package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NotificationConverter
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto
import ru.novemis.rpgapp.repository.procedure.NotificationRepository
import java.util.*

@Component
class NotificationService(
        private val repository: NotificationRepository,
        private val converter: NotificationConverter
) {

    fun addNotification(notification: Notification) {
        repository.save(notification)
    }

    fun pullNotifications(toWhom: Long, from: Date): List<NotificationDto> {
        return repository.findByRecipientIdAndCreationDateAfter(toWhom, from)
                .onEach { repository.delete(it) }
                .map { converter.toDto(it) }
    }

}