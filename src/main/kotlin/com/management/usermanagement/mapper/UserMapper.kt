package com.management.usermanagement.mapper

import com.management.usermanagement.entity.User
import com.management.usermanagement.model.request.UserRequest
import com.management.usermanagement.model.response.UserResponse
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toUserResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id.toString(),
            username = user.username,
            roles = user.roles
        )
    }

    fun toUser(userRequest: UserRequest): User {
        return User(
            username = userRequest.username,
            password = userRequest.password,
            roles = userRequest.roles
        )
    }
}
