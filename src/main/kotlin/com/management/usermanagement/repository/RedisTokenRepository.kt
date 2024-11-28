package com.management.usermanagement.service.impl

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.concurrent.TimeUnit

@Repository
class RedisTokenRepository(private val redisTemplate: RedisTemplate<String, String>) {

    fun saveToken(username: String, token: String, expiration: Long) {
        redisTemplate.opsForValue().set(username, token, expiration, TimeUnit.MILLISECONDS)
    }

    fun getToken(username: String): String? {
        return redisTemplate.opsForValue().get(username)
    }

    fun deleteToken(username: String) {
        redisTemplate.delete(username)
    }

    fun blackListToken(token: String ,expiration: Long ) {
        redisTemplate.opsForValue().set("blacklisted:$token", token, expiration)
    }

    fun isTokenValid(username: String, token: String): Boolean {
        val storedToken = getToken(username)
        return storedToken == token
    }
}
