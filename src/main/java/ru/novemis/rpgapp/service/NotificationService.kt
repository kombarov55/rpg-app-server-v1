package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NotificationConverter
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto
import ru.novemis.rpgapp.repository.procedure.NotificationRepository

@Component
class NotificationService(
        private val repository: NotificationRepository,
        private val converter: NotificationConverter
) {

    fun send(notification: Notification) {
        repository.save(notification)
    }

    fun pull(toWhom: Long): List<NotificationDto> {
        return repository.findByRecipientId(toWhom)
                .onEach { repository.delete(it) }
                .map { converter.toDto(it) }
    }

}