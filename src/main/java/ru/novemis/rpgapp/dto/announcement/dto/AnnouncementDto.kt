package ru.novemis.rpgapp.dto.announcement.dto

data class AnnouncementDto(
        var id: String = "",
        var authorId: Long = 1,
        var imgSrc: String = "",
        var authorFullName: String = "",
        var creationDate: Long = 0,
        var title: String = "",
        var description: String = "",
        var sex: String? = null,
        var gameType: String = "",
        var minAge: Int? = null,
        var maxAge: Int? = null,
        var anonymous: Boolean = false,
        var commentsEnabled: Boolean = true,
        var commentsCount: Long = 0

)