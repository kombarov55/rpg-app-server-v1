package ru.novemis.rpgapp.dto.conversation

data class MessageForm(
        var authorId: Long = 0,
        var text: String = ""
)