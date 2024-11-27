package com.management.usermanagement.repository

import com.management.usermanagement.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}