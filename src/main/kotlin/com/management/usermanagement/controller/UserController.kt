package com.management.usermanagement.controller

import com.management.usermanagement.model.response.UserResponse
import com.management.usermanagement.model.request.UserRequest
import com.management.usermanagement.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getAllUsers(): List<UserResponse> = userService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): UserResponse? = userService.getUserById(id)

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): UserResponse = userService.createUser(userRequest)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userRequest: UserRequest): UserResponse =
        userService.updateUser(id, userRequest)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String) = userService.deleteUser(id)
}
