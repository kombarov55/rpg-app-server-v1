package ru.novemis.rpgapp.security

import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import ru.novemis.rpgapp.util.JWTUtil

@Component
class JWTAuthManager(
        private val jwtUtil: JWTUtil
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        val username = jwtUtil.getUsernameFromToken(authToken)
        val authorities = jwtUtil.getRolesFromToken(authToken).map { SimpleGrantedAuthority(it.name) }

        return if (jwtUtil.validateToken(authToken)) {
            Mono.just(UsernamePasswordAuthenticationToken(
                    username,
                    "",
                    authorities))
        } else {
            throw AccountExpiredException("Auth token has been expired")
        }
    }
}