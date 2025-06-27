package com.guilherme774.jwttokens.repository

import com.guilherme774.jwttokens.domain.enums.UserRoles
import com.guilherme774.jwttokens.domain.user.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
interface UserRepository: CrudRepository<User, Int> {
    @Query("SELECT a FROM User AS a")
    fun getAllUsers(): List<User>

    @Query("SELECT a FROM User AS a WHERE a.email = :email")
    fun findByEmail(@Param("email") email: String): User?


    //fun save(user: User): Boolean { }
}