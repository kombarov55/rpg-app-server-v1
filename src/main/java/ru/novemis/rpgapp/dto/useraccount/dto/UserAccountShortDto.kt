package ru.novemis.rpgapp.dto.useraccount.dto

data class UserAccountShortDto(
        val id: String,
        val vkUserId: Long,
        val fullName: String,
        val img: String,
        val role: String
)