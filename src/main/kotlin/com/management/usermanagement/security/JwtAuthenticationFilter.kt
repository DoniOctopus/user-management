package com.management.usermanagement.security

import com.management.usermanagement.service.impl.RedisTokenRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.math.log

class JwtAuthenticationFilter(private val jwtUtil: JwtUtil , private val redisTokenRepository: RedisTokenRepository) : OncePerRequestFilter() {

    @Throws(ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = getJwtFromRequest(request)

        if (redisTokenRepository.getToken("blacklisted:${token}") != null) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.writer.write("This token is blacklisted. Please log in again.")
            return
        }

        try {
            if (token != null && jwtUtil.validateToken(token)) {
                val username = jwtUtil.extractUsername(token)
                val authentication = UsernamePasswordAuthenticationToken(username, null, emptyList())
                SecurityContextHolder.getContext().authentication = authentication
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
        filterChain.doFilter(request, response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}
