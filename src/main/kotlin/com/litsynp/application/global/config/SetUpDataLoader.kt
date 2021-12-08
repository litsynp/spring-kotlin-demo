package com.litsynp.application.global.config

import com.litsynp.application.domain.user.entity.ERole
import com.litsynp.application.domain.user.entity.Role
import com.litsynp.application.domain.user.repository.RoleRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class SetUpDataLoader(
    var roleRepository: RoleRepository
) : ApplicationListener<ContextRefreshedEvent> {

    var alreadySetup = false

    @Transactional
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (alreadySetup) {
            return
        }

        roleRepository.save(Role(ERole.ROLE_USER))
        roleRepository.save(Role(ERole.ROLE_ADMIN))

        alreadySetup = true
    }
}
