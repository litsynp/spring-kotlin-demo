package com.litsynp.application.domain.auth.dto.request

import javax.validation.constraints.NotBlank

data class LoginRequestDto(
    @NotBlank
    var email: String,

    @NotBlank
    var password: String,
)
