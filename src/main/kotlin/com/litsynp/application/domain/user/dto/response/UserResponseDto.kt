package com.litsynp.application.domain.user.dto.response

import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UserResponseDto(
    val id: UUID,

    @field:Email
    @field:NotEmpty
    val email: String,

    @field:NotNull
    val roles: MutableSet<RoleResponseDto>
)
