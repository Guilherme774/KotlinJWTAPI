package com.guilherme774.jwttokens.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenRepository {
    private val tokens = mutableMapOf<String, UserDetails>()

    fun findUserDetailsByToken(token: String): UserDetails? =
        tokens[token]

    fun save(token: String, user: UserDetails) {
        tokens[token] = user
    }
}