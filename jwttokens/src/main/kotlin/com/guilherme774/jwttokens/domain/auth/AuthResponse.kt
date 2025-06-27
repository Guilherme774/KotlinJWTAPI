package com.guilherme774.jwttokens.domain.auth

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
