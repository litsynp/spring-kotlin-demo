package com.litsynp.application.domain.user.dto

import com.litsynp.application.domain.user.dto.request.SignUpRequestDto
import com.litsynp.application.domain.user.dto.response.UserResponseDto
import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun toEntity(dto: SignUpRequestDto): User {
        return User(email = dto.email, password = dto.password, role = Role.USER)
    }

    fun toUserResponseDto(user: User): UserResponseDto {
        return UserResponseDto(id = user.id, email = user.email, role = user.role)
    }
}
