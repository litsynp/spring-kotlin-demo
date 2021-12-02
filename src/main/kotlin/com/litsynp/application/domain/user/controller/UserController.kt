package com.litsynp.application.domain.user.controller

import com.litsynp.application.domain.user.dto.UserMapper
import com.litsynp.application.domain.user.dto.request.SignUpRequestDto
import com.litsynp.application.domain.user.dto.response.UserResponseDto
import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    val userService: UserService,
    val userMapper: UserMapper
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): UserResponseDto {

        val user = userService.findOneById(id)
        return userMapper.toUserResponseDto(user)
    }

    @PostMapping
    fun signUp(@RequestBody requestDto: SignUpRequestDto): UserResponseDto {

        val user = userService.signup(userMapper.toEntity(requestDto))
        return userMapper.toUserResponseDto(user)
    }
}
