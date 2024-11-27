package com.management.usermanagement.service.impl

import com.management.usermanagement.mapper.UserMapper
import com.management.usermanagement.model.request.UserRequest
import com.management.usermanagement.model.response.UserResponse
import com.management.usermanagement.repository.UserRepository
import com.management.usermanagement.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository, private val userMapper: UserMapper) : UserService {

    override fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { userMapper.toUserResponse(it) }
    }

    override fun getUserById(id: String): UserResponse? {
        return userRepository.findById(id).orElse(null)?.let { userMapper.toUserResponse(it) }
    }

    override fun createUser(userRequest: UserRequest): UserResponse {
        val user = userMapper.toUser(userRequest)
        val savedUser = userRepository.save(user)
        return userMapper.toUserResponse(savedUser)
    }

    override fun updateUser(id: String, userRequest: UserRequest): UserResponse {
        val existingUser = userRepository.findById(id).orElseThrow {
            IllegalArgumentException("User with ID $id not found")
        }
        val updatedUser = existingUser.copy(
            username = userRequest.username,
            password = userRequest.password,
            roles = userRequest.roles
        )
        val savedUser = userRepository.save(updatedUser)
        return userMapper.toUserResponse(savedUser)
    }

    override fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }
}
