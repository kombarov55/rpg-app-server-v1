package ru.novemis.rpgapp.dto.announcement

data class AnnouncementForm(
        var authorId: Long = 1,
        var title: String = "",
        var gameType: String = "",
        var sex: String? = null,
        var minAge: Int? = null,
        var maxAge: Int? = null,
        var description: String = "",
        var anonymous: Boolean = false,
        var commentsEnabled: Boolean = true
)