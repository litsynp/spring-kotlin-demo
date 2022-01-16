package com.litsynp.application.domain.user.dto

import javax.validation.constraints.NotBlank

class RoleDto {
    class Request(
        @NotBlank
        val role: String
    )

    class Response(
        @NotBlank
        val role: String
    )
}
