package com.litsynp.application.domain.user.controller

import com.litsynp.application.domain.user.dto.UserDto
import com.litsynp.application.domain.user.mapper.UserMapper
import com.litsynp.application.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/users")
class UserController(
    val userService: UserService,
    val userMapper: UserMapper,
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): UserDto.Response {

        val user = userService.findOneById(id)
        return userMapper.toUserResponseDto(user)
    }

    @PostMapping
    fun signUp(@Valid @RequestBody requestDto: UserDto.SignUpRequest): ResponseEntity<UserDto.Response> {

        val user = userService.signup(userMapper.toEntity(requestDto))
        return ResponseEntity.ok(userMapper.toUserResponseDto(user))
    }
}
