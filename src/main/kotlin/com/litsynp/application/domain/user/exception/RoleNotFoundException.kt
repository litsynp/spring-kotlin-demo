package com.litsynp.application.domain.user.exception

class RoleNotFoundException : RuntimeException {

    constructor(
        name: String
    ) : super("Role with name $name is not found.")
}
