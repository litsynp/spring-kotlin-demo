package com.litsynp.application.domain.user.entity

import com.litsynp.application.global.entity.BaseTimeEntity
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "account", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
data class User(
    @Email
    @NotEmpty
    var email: String,

    var password: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
) : BaseTimeEntity() {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID = UUID.randomUUID()

    fun update(@Email @NotEmpty email: String, password: String, roles: MutableSet<Role>): User {
        this.email = email
        this.password = password
        this.roles = roles
        return this
    }
}
