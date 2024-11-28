package com.management.usermanagement.service.impl

import com.management.usermanagement.entity.User
import com.management.usermanagement.model.request.LoginRequest
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
    private val jwtUtil: JwtUtil,
    private val redisTokenRepository: RedisTokenRepository
) : AuthService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val user = userRepository.findByUsername(loginRequest.username)
            ?: throw IllegalArgumentException("Invalid username or password")

        if (!passwordEncoder.matches(loginRequest.password, user.password)) {
            throw IllegalArgumentException("Invalid username or password")
        }

        val token = jwtUtil.generateToken(user.username)

        return LoginResponse(message = "Login success", token = token)
    }

    override fun register(user: User): User {
        if (userRepository.existsByUsername(user.username)) {
            throw IllegalArgumentException("Username is already taken")
        }
        val encodedUser = user.copy(password = passwordEncoder.encode(user.password))
        return userRepository.save(encodedUser)
    }

    override fun logout(bearerToken: String) {
        val token = jwtUtil.parseToken(bearerToken)

        if (token != null && jwtUtil.validateToken(token)) {
            val expired = jwtUtil.getExpirationTime(token)
            redisTokenRepository.blackListToken(token,expired)
            return
        }
        throw RuntimeException("Invalid token");
    }
}
