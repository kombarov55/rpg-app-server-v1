package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto

@Component
class NotificationConverter {

    fun toDto(domain: Notification): NotificationDto {
        return NotificationDto(
                id = domain.id,
                text = domain.text,
                severity = domain.severity,
                creationDate = domain.creationDate.time
        )
    }

}