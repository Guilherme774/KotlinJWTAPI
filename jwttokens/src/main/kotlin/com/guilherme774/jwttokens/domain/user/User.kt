package com.guilherme774.jwttokens.domain.user

import com.guilherme774.jwttokens.domain.enums.UserRoles
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "Users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var role: UserRoles = UserRoles.USER
)
