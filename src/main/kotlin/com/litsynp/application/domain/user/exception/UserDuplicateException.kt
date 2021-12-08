package com.litsynp.application.domain.user.exception

class UserDuplicateException : RuntimeException {

    constructor(
        property: String,
        value: String
    ) : super("User with property $property and value $value already exists.")
}
