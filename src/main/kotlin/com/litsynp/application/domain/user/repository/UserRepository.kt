package com.litsynp.application.domain.user.repository

import com.litsynp.application.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findOneById(id: UUID): Optional<User>
}
