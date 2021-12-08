package com.litsynp.application.domain.auth.dto.response

import java.util.*

data class JwtResponseDto(
    var token: String,
    var id: UUID,
    var email: String,
    var roles: MutableList<String>
) {
    var type: String = "Bearer"
}
