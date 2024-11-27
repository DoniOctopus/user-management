package com.management.usermanagement.service

import com.management.usermanagement.model.request.LoginRequest
import com.management.usermanagement.entity.User
import com.management.usermanagement.model.response.LoginResponse

interface AuthService {
    fun login(loginRequest: LoginRequest): LoginResponse
    fun register(user: User): User
}
