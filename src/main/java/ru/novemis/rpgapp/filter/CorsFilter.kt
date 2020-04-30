package ru.novemis.rpgapp.filter

import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class CorsFilter : WebFilter {

    override fun filter(serverWebExchange: ServerWebExchange, webFilterChain: WebFilterChain): Mono<Void> {
        serverWebExchange.response.headers["Access-Control-Allow-Origin"] = "*"
        serverWebExchange.response.headers["Access-Control-Allow-Credentials"] = "true"
        serverWebExchange.response.headers["Access-Control-Allow-Methods"] = "GET,HEAD,OPTIONS,POST,PUT,DELETE,PATCH"
        serverWebExchange.response.headers["Access-Control-Allow-Headers"] = "*"

        return webFilterChain.filter(serverWebExchange)
    }
}