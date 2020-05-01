package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.conversation.Conversation
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.conversation.ConversationDto

@Component
class ConversationConverter {

    fun toDto(author: UserAccount, conversation: Conversation): ConversationDto {
        return conversation.let {
            val companion = if (conversation.user1?.id!! == author.id)
                conversation.user2!! else
                conversation.user1!!

            ConversationDto(
                    id = it.id,
                    companionImgSrc = companion.photo50Url,
                    companionFullName = companion.let { it.firstName + " " + it.lastName }
            )
        }
    }

}