package ru.novemis.rpgapp.dto.conversation

data class MessageDto(
        var id: String = "",
        var authorUserId: Long = 0,
        var authorImgSrc: String = "",
        var authorFullName: String = "",
        var creationDate: Long = 0,
        var text: String = ""
)