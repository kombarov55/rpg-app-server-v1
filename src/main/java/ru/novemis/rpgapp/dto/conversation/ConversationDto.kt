package ru.novemis.rpgapp.dto.conversation

import java.util.*

data class ConversationDto(
        var id: String = "",
        var companionImgSrc: String = "",
        var companionFullName: String = "",
        var lastMsgDate: Date? = null,
        var lastMsgText: String? = null
)