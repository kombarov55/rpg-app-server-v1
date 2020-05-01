package ru.novemis.rpgapp.dto.conversation

data class ConversationDto(
        var id: String = "",
        var companionImgSrc: String = "",
        var companionFullName: String = "",
        var lastMsgDate: Long? = null,
        var lastMsgText: String? = null
)