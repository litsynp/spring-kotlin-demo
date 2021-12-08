package com.litsynp.application.global.security.services

import com.litsynp.application.domain.user.entity.User
import com.litsynp.application.domain.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(
    var userRepository: UserRepository
) : UserDetailsService {
    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findOneByEmail(email = username)
            .orElseThrow {
                UsernameNotFoundException("User not found with email: " + username)
            }

        return UserDetailsImpl.build(user)
    }
}
