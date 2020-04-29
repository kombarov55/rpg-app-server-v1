package ru.novemis.rpgapp.dto.announcement

class AnnouncementDto(
        var id: String? = null,
        var authorId: Int = -1,
        val imgSrc: String? = null,
        val authorFullName: String? = null,
        val creationDate: Long = System.currentTimeMillis(),
        val title: String = "",
        val description: String = "",
        val sex: String? = null,
        val gameType: String = "",
        val minAge: Int? = null,
        val maxAge: Int? = null,
        val anonymous: Boolean = false,
        val commentsEnabled: Boolean = true
)