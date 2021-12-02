package com.litsynp.application.domain.user.entity

enum class Role(
    val key: String,
    val title: String
) {
    ADMIN("ROLE_ADMIN", "Admin role"),
    USER("ROLE_USER", "User role")
}
