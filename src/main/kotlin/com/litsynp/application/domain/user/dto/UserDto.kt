package com.litsynp.application.domain.user.dto

import java.util.*
import javax.validation.constraints.*

class UserDto {
    class SignUpRequest(
        @field:Email
        @field:NotBlank
        @field:Size(max = 50)
        val email: String,

        @field:NotBlank
        @field:Size(min = 6, max = 40)
        val password: String,

        val roles: Set<RoleDto.Request>
    )

    class Response(
        val id: UUID,

        @field:Email
        @field:NotEmpty
        val email: String,

        @field:NotNull
        val roles: MutableSet<RoleDto.Response>
    )
}
