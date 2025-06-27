package com.guilherme774.jwttokens.domain.auth

data class AuthRequest(
    val email: String,
    val password: String,
)
