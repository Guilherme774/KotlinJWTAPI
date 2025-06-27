package com.guilherme774.jwttokens.services.user

import com.guilherme774.jwttokens.domain.user.User
import com.guilherme774.jwttokens.domain.user.UserDTO
import com.guilherme774.jwttokens.repository.UserRepository
import com.guilherme774.jwttokens.utils.exception.UserExcpetion
import com.guilherme774.jwttokens.utils.mapper.UserMapper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val encoder: PasswordEncoder
): UserService {
    override fun getUsers(): List<UserDTO> {
        val users = userRepository.getAllUsers()

        if(users.isEmpty())
            throw UserExcpetion("Oh no, we haven't found any users yet!")

        return users.map {
            userMapper.fromEntity(it)
        }
    }

    override fun createUser(user: UserDTO): UserDTO {
        if(user.id != 0)
            throw UserExcpetion("Oh no, that ID is not valid for create an User!")

        val userExists = getUserByEmail(user.email)

        if(userExists)
            throw UserExcpetion("Hey wait a minute, that user has been created!")

        val userPasswordEncrypted = user.copy(password = encoder.encode(user.password))
        val userToAdd = userMapper.toEntity(userPasswordEncrypted)
        userRepository.save(userToAdd)
        return userMapper.fromEntity(userToAdd)
    }

    override fun getUserById(id: Int): UserDTO {
        val selectedUser = userRepository.findById(id)
        val user = selectedUser.orElseThrow { UserExcpetion("Oh no, that user haven't been found!") }

        return userMapper.fromEntity(user)
    }

    override fun getUserByEmail(email: String): Boolean {
        val users = userRepository.getAllUsers()

        return users.any { it.email == email }
    }

    override fun updateUser(user: UserDTO): UserDTO {
        val userExists = userRepository.existsById(user.id)

        if(!userExists)
            throw UserExcpetion("Oh no, that user haven't been found!")

        val userToUpdate = userMapper.toEntity(user)
        userRepository.save(userToUpdate)

        return user
    }

    override fun deleteUser(id: Int) {
        val userExists = userRepository.existsById(id)

        if(!userExists)
            throw UserExcpetion("Oh no, that user haven't been found")

        userRepository.deleteById(id)
    }

}