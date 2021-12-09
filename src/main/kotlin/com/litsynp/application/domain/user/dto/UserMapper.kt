package com.litsynp.application.domain.user.dto

import com.litsynp.application.domain.user.dto.request.SignUpRequestDto
import com.litsynp.application.domain.user.dto.response.UserResponseDto
import com.litsynp.application.domain.user.entity.ERole
import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.exception.RoleNotFoundException
import com.litsynp.application.domain.user.repository.RoleRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.HashSet
import java.util.function.Consumer

@Component
class UserMapper(
    val roleRepository: RoleRepository
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun toEntity(dto: SignUpRequestDto): User {

        val strRoles: Set<String> = dto.roles
        val roles: MutableSet<Role> = HashSet<Role>()

        if (strRoles.isEmpty()) {
            val userRole: Role = roleRepository.findOneByName(ERole.ROLE_USER)
                .orElseThrow { RoleNotFoundException(ERole.ROLE_USER.name) }
            roles.add(userRole)
        } else {
            strRoles.forEach(Consumer { role: String? ->
                when (role) {
                    "admin" -> {
                        val adminRole: Role = roleRepository.findOneByName(ERole.ROLE_ADMIN)
                            .orElseThrow { RoleNotFoundException(ERole.ROLE_ADMIN.name) }
                        roles.add(adminRole)
                    }
                    else -> {
                        val userRole: Role = roleRepository.findOneByName(ERole.ROLE_USER)
                            .orElseThrow { RoleNotFoundException(ERole.ROLE_USER.name) }
                        roles.add(userRole)
                    }
                }
            })
        }

        return User(email = dto.email, password = dto.password, roles = roles)
    }

    fun toUserResponseDto(user: User): UserResponseDto {
        return UserResponseDto(
            id = user.id,
            email = user.email,
            roles = user.roles.map { role -> role.name.name }.toMutableSet()
        )
    }
}
