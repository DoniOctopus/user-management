package com.management.usermanagement.service

import com.management.usermanagement.model.response.UserResponse
import com.management.usermanagement.model.request.UserRequest

interface UserService {
    fun getAllUsers(): List<UserResponse>
    fun getUserById(id: String): UserResponse?
    fun createUser(userRequest: UserRequest): UserResponse
    fun updateUser(id: String, userRequest: UserRequest): UserResponse
    fun deleteUser(id: String)
}
