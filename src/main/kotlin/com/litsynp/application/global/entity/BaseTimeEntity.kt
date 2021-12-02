package com.litsynp.application.global.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {

    @CreatedDate
    @Column(insertable = false, updatable = false)
    lateinit var createdOn: LocalDateTime

    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    lateinit var modifiedOn: LocalDateTime
}
