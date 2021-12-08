package com.litsynp.application.domain.user.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["name"])])
data class Role(
    @Enumerated(EnumType.STRING)
    var name: ERole
) {
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    @Type(type = "uuid-char")
    val id: UUID = UUID.randomUUID()
}
