package com.litsynp.application.domain.user.dto.request

import javax.validation.constraints.NotBlank

data class RoleRequestDto(
    @NotBlank
    val role: String
)
