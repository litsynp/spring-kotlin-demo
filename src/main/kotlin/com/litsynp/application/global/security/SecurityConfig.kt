package com.litsynp.application.global.security

import com.litsynp.application.global.security.jwt.AuthenticationTokenFilter
import com.litsynp.application.global.security.jwt.JwtAuthenticationEntryPoint
import com.litsynp.application.global.security.services.UserDetailsServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.access.expression.SecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
class SecurityConfig(
    private val environment: Environment
) : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired
    private lateinit var unauthorizedHandler: JwtAuthenticationEntryPoint

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun authenticationJwtTokenFilter(): AuthenticationTokenFilter {
        return AuthenticationTokenFilter()
    }

    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun roleHierarchy(): RoleHierarchy? {
        val roleHierarchy = RoleHierarchyImpl()
        val roleHierarchyMap: MutableMap<String, List<String>> = HashMap()
        roleHierarchyMap["ROLE_ADMIN"] = Arrays.asList("ROLE_USER")
        val roles = RoleHierarchyUtils.roleHierarchyFromMap(roleHierarchyMap)
        log.info(roles)
        roleHierarchy.setHierarchy(roles)
        return roleHierarchy
    }

    @Bean
    fun expressionHandler(): SecurityExpressionHandler<FilterInvocation?>? {
        val webSecurityExpressionHandler = DefaultWebSecurityExpressionHandler()
        webSecurityExpressionHandler.setRoleHierarchy(roleHierarchy())
        return webSecurityExpressionHandler
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        if (environment.activeProfiles.contains("local")) {
            http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
        }

        http
            .cors()
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers().frameOptions().disable() // enable H2-console
            .and()
            .authorizeRequests().expressionHandler(expressionHandler())
            .antMatchers("/api/v1/auth/**").permitAll()
            .anyRequest().permitAll()
            .and()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
            .and()
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
