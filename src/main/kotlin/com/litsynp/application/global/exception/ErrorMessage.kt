package com.litsynp.application.global.exception

import org.springframework.validation.FieldError
import java.time.LocalDateTime

class ErrorMessage(message: String, errors: MutableList<FieldError>? = null) {

    var message: String? = message
    var date: LocalDateTime = LocalDateTime.now()
    var errors: MutableList<MutableMap<String, String?>>? = null

    init {
        if (errors != null) {
            this.errors =
                errors.map { mutableMapOf(it.field to it.defaultMessage) }
                    .toMutableList()
        }
    }
}
