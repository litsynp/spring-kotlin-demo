package com.litsynp.application.domain.user.controller

import com.litsynp.application.domain.user.dto.UserMapper
import com.litsynp.application.domain.user.dto.request.SignUpRequestDto
import com.litsynp.application.domain.user.dto.response.UserResponseDto
import com.litsynp.application.domain.user.entity.ERole
import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.exception.RoleNotFoundException
import com.litsynp.application.domain.user.exception.UserDuplicateException
import com.litsynp.application.domain.user.repository.RoleRepository
import com.litsynp.application.domain.user.repository.UserRepository
import com.litsynp.application.domain.user.service.UserService
import com.litsynp.application.global.security.jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    val userService: UserService,
    val userRepository: UserRepository,
    val userMapper: UserMapper,
    val jwtUtils: JwtUtils
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): UserResponseDto {

        val user = userService.findOneById(id)
        return userMapper.toUserResponseDto(user)
    }

    @PostMapping
    fun signUp(@Valid @RequestBody requestDto: SignUpRequestDto): ResponseEntity<UserResponseDto> {

        val user = userService.signup(userMapper.toEntity(requestDto))
        return ResponseEntity.ok(userMapper.toUserResponseDto(user))
    }
}
