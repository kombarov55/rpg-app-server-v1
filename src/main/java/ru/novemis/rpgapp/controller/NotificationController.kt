package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto
import ru.novemis.rpgapp.service.NotificationService
import ru.novemis.rpgapp.util.JWTUtil

@RestController
class NotificationController(
        private val notificationService: NotificationService,
        private val jwtUtil: JWTUtil
) {

    @GetMapping("/notification")
    fun pull(
            @RequestHeader("Authorization") jwtToken: String
    ): List<NotificationDto> {
        val userId = jwtUtil.getUserIdFromRawToken(jwtToken)
        return notificationService.pullNotifications(userId)
    }

}