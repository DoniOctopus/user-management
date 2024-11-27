package com.management.usermanagement.model.request

data class UserRequest(
    val username: String,
    val password: String,
    val roles: List<String> = listOf("USER")
)
