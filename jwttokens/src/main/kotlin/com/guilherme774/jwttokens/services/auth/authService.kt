package com.guilherme774.jwttokens.services.auth

import com.guilherme774.jwttokens.config.JwtProperties
import com.guilherme774.jwttokens.domain.auth.AuthRequest
import com.guilherme774.jwttokens.domain.auth.AuthResponse
import com.guilherme774.jwttokens.repository.RefreshTokenRepository
import com.guilherme774.jwttokens.services.user.CustomUserDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun authenticateUser(authRequest: AuthRequest): AuthResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)

        return AuthResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    fun generateAccessToken(user: UserDetails): String = tokenService.generateToken(
        user = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )

    fun generateRefreshToken(user: UserDetails): String = tokenService.generateToken(
        user = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)

        return extractedEmail?.let { email ->
            val currentUserDeatils = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)

            if(!tokenService.isExpired(token) && currentUserDeatils.username == refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDeatils)
            else
                null
        }
    }
}