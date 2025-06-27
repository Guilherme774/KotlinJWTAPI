package com.guilherme774.jwttokens.services.user

import com.guilherme774.jwttokens.domain.user.UserDTO
import com.guilherme774.jwttokens.repository.UserRepository
import org.springframework.stereotype.Service

interface UserService {
    fun getUsers(): List<UserDTO>
    fun createUser(user: UserDTO): UserDTO
    fun getUserById(id: Int): UserDTO
    fun getUserByEmail(email: String): Boolean
    fun updateUser(user: UserDTO): UserDTO
    fun deleteUser(id: Int)
}