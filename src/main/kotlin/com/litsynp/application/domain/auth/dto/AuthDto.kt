package com.litsynp.application.domain.auth.dto

import java.util.*
import javax.validation.constraints.NotBlank

class AuthDto {
    data class LoginRequest(
        @field:NotBlank
        var email: String,

        @field:NotBlank
        var password: String,
    )

    data class JwtResponse(
        var token: String,
        var id: UUID,
        var email: String,
        var roles: MutableList<String>
    ) {
        var type: String = "Bearer"
    }
}
