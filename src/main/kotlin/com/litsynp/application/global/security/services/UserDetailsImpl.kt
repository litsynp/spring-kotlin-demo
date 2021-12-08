package com.litsynp.application.global.security.services

import com.fasterxml.jackson.annotation.JsonIgnore
import com.litsynp.application.domain.user.entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors

class UserDetailsImpl(
    var id: UUID,
    var email: String,
    @JsonIgnore
    private var password: String,
    private var authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {
    companion object {
        private const val serialVersionUID: Long = 1L

        fun build(user: User): UserDetailsImpl {
            val authorities: MutableList<GrantedAuthority> = user.roles.stream()
                .map { role -> SimpleGrantedAuthority(role.name.name) }
                .collect(Collectors.toList())

            return UserDetailsImpl(
                user.id,
                user.email,
                user.password,
                authorities
            )
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return id.toString()
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
