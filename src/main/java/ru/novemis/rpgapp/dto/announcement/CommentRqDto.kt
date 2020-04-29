package ru.novemis.rpgapp.dto.announcement

data class CommentRqDto(
        var authorId: Long = 1,
        var announcementId: String = "",
        var text: String = ""
)