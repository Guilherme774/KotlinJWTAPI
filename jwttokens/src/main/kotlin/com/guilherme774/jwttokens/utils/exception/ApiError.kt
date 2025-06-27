package com.guilherme774.jwttokens.utils.exception

import org.springframework.http.HttpStatus

data class ApiError (
    private val _message: String?,
    val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    val code: Int = status.value()
) {
    val message: String
        get() = _message ?: "Oh no, we got an error!"
}