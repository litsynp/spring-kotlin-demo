package com.litsynp.application.domain.auth.controller

import com.litsynp.application.domain.auth.dto.request.LoginRequestDto
import com.litsynp.application.domain.auth.dto.response.JwtResponseDto
import com.litsynp.application.global.security.jwt.JwtUtils
import com.litsynp.application.global.security.services.UserDetailsImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authenicationManager: AuthenticationManager,
    val jwtUtils: JwtUtils
) {
    @PostMapping("/tokens")
    fun login(@Valid @RequestBody requestDto: LoginRequestDto): ResponseEntity<JwtResponseDto> {

        val authentication = authenicationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                requestDto.email,
                requestDto.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils.generateJwtToken(authentication)

        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority? -> item!!.authority }
            .collect(Collectors.toList())

        return ResponseEntity.ok(
            JwtResponseDto(
                token = jwt,
                id = userDetails.id,
                email = userDetails.email,
                roles = roles
            )
        )
    }
}
