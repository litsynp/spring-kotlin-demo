package com.litsynp.application.domain.user.dto.response

import com.litsynp.application.domain.user.entity.Role
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UserResponseDto(
    val id: UUID,

    @Email
    @NotEmpty
    val email: String,

    @NotNull
    val role: Role
)
