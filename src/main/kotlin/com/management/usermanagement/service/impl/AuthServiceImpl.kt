package com.management.usermanagement.service.impl

import com.management.usermanagement.model.request.LoginRequest
import com.management.usermanagement.entity.User
import com.management.usermanagement.model.response.LoginResponse
import com.management.usermanagement.repository.UserRepository
import com.management.usermanagement.security.JwtUtil
import com.management.usermanagement.service.AuthService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) : AuthService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val user = userRepository.findByUsername(loginRequest.username)
        if (user == null) {
            return LoginResponse(
                message = "Username not found",
                token = ""
            )
        }
        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            return LoginResponse(
                message = "Wrong password",
                token = ""
            )
        }

        val token = jwtUtil.generateToken(user.username)
        return LoginResponse(
            message = "Success login",
            token = token
        )
    }


    override fun register(user: User): User {
        if (userRepository.existsByUsername(user.username)) {
            throw IllegalArgumentException("Username is already taken")
        }
        val encodedUser = user.copy(password = passwordEncoder.encode(user.password))
        return userRepository.save(encodedUser)
    }
}
