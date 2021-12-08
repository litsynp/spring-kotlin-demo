package com.litsynp.application.domain.user.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class SignUpRequestDto(
    @Email
    @NotBlank
    @Size(max = 50)
    val email: String,

    @NotBlank
    @Size(min = 6, max = 40)
    val password: String,

    val roles: Set<String>
)
