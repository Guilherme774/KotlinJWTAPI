package com.guilherme774.jwttokens.utils.mapper

import com.guilherme774.jwttokens.domain.user.User
import com.guilherme774.jwttokens.domain.user.UserDTO
import org.springframework.stereotype.Component

@Component
class UserMapper: Mapper<UserDTO, User> {
    override fun fromEntity(entity: User): UserDTO {
        return UserDTO(
            entity.id,
            username = entity.username,
            email = entity.email,
            password = entity.password,
            role = entity.role
        )
    }

    override fun toEntity(domain: UserDTO): User {
        return User(
            domain.id,
            username = domain.username,
            email = domain.email,
            password = domain.password,
            role = domain.role
        )
    }
}