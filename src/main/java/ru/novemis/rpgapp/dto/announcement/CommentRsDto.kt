package ru.novemis.rpgapp.dto.announcement

data class CommentRsDto(
        var announcementId: String = "",
        var authorFullName: String = "",
        var authorImgSrc: String = "",
        var creationDate: Long = 0,
        var text: String = ""
)