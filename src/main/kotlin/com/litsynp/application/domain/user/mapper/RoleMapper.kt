package com.litsynp.application.domain.user.mapper

import com.litsynp.application.domain.user.dto.RoleDto
import com.litsynp.application.domain.user.entity.ERole
import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.exception.RoleNotFoundException
import com.litsynp.application.domain.user.repository.RoleRepository
import org.springframework.stereotype.Component

@Component
class RoleMapper(
    val roleRepository: RoleRepository
) {
    fun toEntity(dto: RoleDto.Request): Role {
        return when (dto.role) {
            "admin" -> {
                roleRepository.findOneByName(ERole.ROLE_ADMIN)
                    .orElseThrow { RoleNotFoundException(ERole.ROLE_ADMIN.name) }
            }
            else -> {
                roleRepository.findOneByName(ERole.ROLE_USER)
                    .orElseThrow { RoleNotFoundException(ERole.ROLE_USER.name) }
            }
        }
    }

    fun toResponseDto(role: Role): RoleDto.Response {
        return RoleDto.Response(role = role.name.name)
    }
}
