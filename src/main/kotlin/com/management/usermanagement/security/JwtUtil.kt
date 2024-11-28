package com.management.usermanagement.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit

@Component
class JwtUtil() {
    private val secretKey = "asdewsdaiuerhriwojduhwefoasjdoheouhqojwfbwiehoi"
    private val expirationMs = 3600000L

    fun generateToken(username: String): String {
        val token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .compact()
        return token
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
                .build()
                .parseClaimsJws(token)
            return true;
        }catch (ex: Exception){
            return false
        }
    }

    fun extractUsername(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun getExpirationTime(token: String): Long {
        val claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray()))
            .build()
            .parseClaimsJws(token)
            .body
        return claims.expiration.time - System.currentTimeMillis()
    }

    fun parseToken(token: String): String? {
        if (token.startsWith("Bearer ")) {
            return token.substring("Bearer ".length)
        }
        return null
    }
}
