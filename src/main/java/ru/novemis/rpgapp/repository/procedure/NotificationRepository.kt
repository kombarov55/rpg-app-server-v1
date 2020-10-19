package ru.novemis.rpgapp.repository.procedure

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.procedure.Notification

interface NotificationRepository : CrudRepository<Notification, String> {

    fun findByRecipientId(recipientId: Long): List<Notification>

}