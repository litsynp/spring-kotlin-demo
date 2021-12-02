package com.litsynp.application.global.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.SecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchy
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.FilterInvocation
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import java.util.*


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val log = LoggerFactory.getLogger(javaClass)

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
        http
            .httpBasic()
            .and()
            .csrf().disable()
            .exceptionHandling()
            .and()
            .headers().frameOptions().disable() // enable H2-console
            .and()
            .authorizeRequests().expressionHandler(expressionHandler())
            .anyRequest().permitAll()

    }
}
