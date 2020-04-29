package ru.novemis.rpgapp.dto.announcement

data class CommentRqDto(
        var authorId: Int = 1,
        var announcementId: String = "",
        var text: String = ""
)