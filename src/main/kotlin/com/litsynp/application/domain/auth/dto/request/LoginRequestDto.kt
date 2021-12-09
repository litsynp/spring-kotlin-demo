package com.litsynp.application.domain.auth.dto.request

import javax.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank
    var email: String,

    @field:NotBlank
    var password: String,
)
