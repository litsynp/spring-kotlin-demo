package com.litsynp.application.domain.user.repository

import com.litsynp.application.domain.user.entity.ERole
import com.litsynp.application.domain.user.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RoleRepository : JpaRepository<Role, UUID> {
    fun findOneByName(name: ERole): Optional<Role>
}
