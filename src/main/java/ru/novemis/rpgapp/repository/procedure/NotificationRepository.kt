package ru.novemis.rpgapp.repository.procedure

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.procedure.Notification
import java.util.*

interface NotificationRepository : CrudRepository<Notification, String> {

    fun findByRecipientIdAndCreationDateAfter(recipientId: Long, from: Date): List<Notification>

}