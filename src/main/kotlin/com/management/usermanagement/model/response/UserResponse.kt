package com.management.usermanagement.model.response

data class UserResponse(
    val id: String,
    val username: String,
    val roles: List<String>
)
