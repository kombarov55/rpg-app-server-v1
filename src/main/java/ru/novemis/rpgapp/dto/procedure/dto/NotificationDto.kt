package ru.novemis.rpgapp.dto.procedure.dto

import ru.novemis.rpgapp.domain.procedure.NotificationSeverity

data class NotificationDto(
        val id: String,
        val text: String,
        val severity: NotificationSeverity,
        val creationDate: Long
)