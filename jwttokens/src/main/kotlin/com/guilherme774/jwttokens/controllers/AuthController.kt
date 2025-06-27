package com.guilherme774.jwttokens.controllers

import com.guilherme774.jwttokens.domain.auth.AuthRequest
import com.guilherme774.jwttokens.domain.auth.AuthResponse
import com.guilherme774.jwttokens.domain.auth.RefreshTokenRequest
import com.guilherme774.jwttokens.domain.auth.TokenResponse
import com.guilherme774.jwttokens.services.auth.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authenticationService: AuthService
){
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthRequest): AuthResponse {
        return authenticationService.authenticateUser(authRequest)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody request: RefreshTokenRequest): TokenResponse =
        authenticationService.refreshAccessToken(request.token)
            ?.mapToAuthResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Hey, that refresh token is invalid")

    private fun String.mapToAuthResponse(): TokenResponse =
        TokenResponse(
            token = this
        )
}