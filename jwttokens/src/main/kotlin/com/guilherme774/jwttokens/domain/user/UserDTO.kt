package com.guilherme774.jwttokens.domain.user

import com.guilherme774.jwttokens.domain.enums.UserRoles

data class UserDTO (
    val id: Int = 0,
    val username: String,
    val email: String,
    val password: String,
    val role: UserRoles
)