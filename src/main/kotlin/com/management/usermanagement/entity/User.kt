package com.management.usermanagement.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "m_user")
data class User(
    @Id
    val id: UUID = UUID.randomUUID(),
    val username: String,
    val password: String,
    val roles: List<String> = listOf("USER")
)