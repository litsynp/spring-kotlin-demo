package com.litsynp.application.domain.user.dto.response

import javax.validation.constraints.NotBlank

data class RoleResponseDto(
    @NotBlank
    val role: String
)
