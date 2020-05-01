package ru.novemis.rpgapp.websocket

import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class EchoHandler : WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> {
        return session
                .send(session.receive()
                        .map { "ECHO " + it.payloadAsText }
                        .map { session.textMessage(it) })
    }
}