package ru.novemis.rpgapp.domain.procedure

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Notification (
        @Id
        var id: String = UUID.randomUUID().toString(),

        var text: String = "",

        var data: String = "",

        var severity: NotificationSeverity = NotificationSeverity.NORMAL,

        var creationDate: Date = Date(),

        var recipientId: Long = -1
) {
    fun parseAndSetData(data: Map<String, Any>) {
        this.data = data.entries.map { pair -> pair.key + pairDelimiter + pair.value}.joinToString(entityDelimiter)
    }

    fun extractData(): Map<String, Any> {
        return data.split(entityDelimiter).map {
            val values = it.split(pairDelimiter)

            values[0] to values[1]
        }.toMap()
    }

    companion object {
        const val pairDelimiter = "$->"
        const val entityDelimiter = "$,"
    }
}