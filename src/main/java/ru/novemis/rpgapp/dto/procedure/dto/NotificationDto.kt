package ru.novemis.rpgapp.dto.procedure.dto

data class NotificationDto(
        val id: String,
        val text: String,
        val data: Map<String, Any>,
        val creationDate: Long
)