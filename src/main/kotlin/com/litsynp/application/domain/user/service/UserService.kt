package com.litsynp.application.domain.user.service

import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    @Transactional
    fun signup(user: User): User {
        val newUser = User(email = user.email, password = user.password, Role.USER)
        return userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    @Transactional(readOnly = true)
    fun findOneById(id: UUID): User {
        return userRepository.findOneById(id).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id=${id}")
        }
    }
}
