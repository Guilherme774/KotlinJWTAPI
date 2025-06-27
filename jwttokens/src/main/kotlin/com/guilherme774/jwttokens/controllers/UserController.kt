package com.guilherme774.jwttokens.controllers

import com.guilherme774.jwttokens.domain.user.User
import com.guilherme774.jwttokens.domain.user.UserDTO
import com.guilherme774.jwttokens.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService
){
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.getUserById(id))
    }

    @PostMapping
    fun createUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    @PutMapping
    fun updateUser(@RequestBody user: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.updateUser(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int): ResponseEntity<Unit> {
        return ResponseEntity.ok(userService.deleteUser(id))
    }
}