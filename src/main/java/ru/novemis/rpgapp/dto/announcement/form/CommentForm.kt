package ru.novemis.rpgapp.dto.announcement.form

data class CommentForm(
        var authorId: Long = 1,
        var announcementId: String = "",
        var text: String = ""
)