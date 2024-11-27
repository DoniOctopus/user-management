package com.management.usermanagement.controller

import com.management.usermanagement.model.request.LoginRequest
import com.management.usermanagement.entity.User
import com.management.usermanagement.model.response.LoginResponse
import com.management.usermanagement.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val loginResponse = authService.login(loginRequest)
        return ResponseEntity.ok(loginResponse)
    }


    @PostMapping("/register/admin")
    fun registerAdmin(@RequestBody user: User): ResponseEntity<User> {
        val registeredUser = authService.register(user.copy(roles = listOf("ADMIN")))
        return ResponseEntity.ok(registeredUser)
    }

    @PostMapping("/register/user")
    fun registerUser(@RequestBody user: User): ResponseEntity<User> {
        val registeredUser = authService.register(user.copy(roles = listOf("USER")))
        return ResponseEntity.ok(registeredUser)
    }
}
