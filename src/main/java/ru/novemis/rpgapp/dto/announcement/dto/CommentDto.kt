package ru.novemis.rpgapp.dto.announcement.dto

data class CommentDto(
        var id: String = "",
        var announcementId: String = "",
        var authorFullName: String = "",
        var authorImgSrc: String = "",
        var creationDate: Long = 0,
        var text: String = "",
        var deleted: Boolean = false
)