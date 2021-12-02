package com.litsynp.application.domain.user.entity

import com.litsynp.application.global.entity.BaseTimeEntity
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "account", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(
    @Email
    @NotEmpty
    var email: String,

    var password: String,

    @Enumerated(EnumType.STRING)
    @NotNull
    var role: Role
) : BaseTimeEntity() {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID = UUID.randomUUID()

    fun update(@Email @NotEmpty email: String, password: String): User {
        this.email = email
        this.password = password
        return this
    }

    fun getRoleKey(): String {
        return this.role.key
    }
}
