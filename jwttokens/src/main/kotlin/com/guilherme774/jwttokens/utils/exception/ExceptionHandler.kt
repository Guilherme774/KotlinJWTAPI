package com.guilherme774.jwttokens.utils.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(excpetion: Exception): ResponseEntity<ApiError> {
        val error = ApiError(excpetion.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(UserExcpetion::class)
    fun userExcpetionHandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }

    @ExceptionHandler(ArticleException::class)
    fun articleExceptionhandler(exception: Exception): ResponseEntity<ApiError> {
        val error = ApiError(exception.message)
        return ResponseEntity(error, error.status)
    }
}