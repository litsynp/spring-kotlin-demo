package com.litsynp.application.domain.user.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class SignUpRequestDto(
    @Email
    @NotEmpty
    val email: String,

    @NotEmpty
    val password: String
)
