package com.guilherme774.jwttokens.services.auth

import com.guilherme774.jwttokens.config.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenService(
    private val jwtProperties: JwtProperties
) {
    private val secreteKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    fun generateToken(
        user: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(user.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secreteKey)
            .compact()

    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    fun isValid(token: String, user: UserDetails): Boolean {
        val email = extractEmail(token)

        return user.username == email && !isExpired(token)
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secreteKey)
            .build()

        return parser.parseSignedClaims(token).payload
    }

}