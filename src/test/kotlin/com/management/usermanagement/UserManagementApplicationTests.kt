package com.management.usermanagement

import com.management.usermanagement.entity.User
import com.management.usermanagement.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer

@Testcontainers
@SpringBootTest
class UserManagementApplicationTests {

    companion object {
        @Container
        val mongoContainer = MongoDBContainer("mongo:6.0.3")

        @JvmStatic
        @DynamicPropertySource
        fun mongoProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { mongoContainer.replicaSetUrl }
        }
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `test user creation`() {
        val user = User(username = "testuser", password = "password123", roles = listOf("USER"))
        val savedUser = userRepository.save(user)
        val fetchedUser = userRepository.findById(savedUser.id.toString()).get()

        assertEquals(user.username, fetchedUser.username)
    }
}