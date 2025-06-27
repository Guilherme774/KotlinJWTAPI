package com.guilherme774.jwttokens.utils.exception

import org.springframework.http.HttpStatus
import java.lang.Exception

class UserExcpetion(override val message: String): Exception(message)