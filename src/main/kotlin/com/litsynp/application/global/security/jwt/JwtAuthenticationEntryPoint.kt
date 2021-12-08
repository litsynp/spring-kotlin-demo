package com.litsynp.application.global.security.jwt

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint : AuthenticationEntryPoint {

    private val log = LoggerFactory.getLogger(javaClass)

    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest, response: HttpServletResponse, authException: AuthenticationException
    ) {
        log.error("Unauthorized error: ${authException.message}")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
