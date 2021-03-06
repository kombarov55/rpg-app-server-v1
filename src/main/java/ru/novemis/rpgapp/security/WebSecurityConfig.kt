package ru.novemis.rpgapp.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
open class WebSecurityConfig(
        private val jwtAuthManager: JWTAuthManager,
        private val securityContextRepository: SecurityContextRepository
) {

    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.csrf().disable()
                .cors().and()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(jwtAuthManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .pathMatchers("/user*").hasRole(VISITOR.name)
                .anyExchange().permitAll()

                .and().build()
    }

}