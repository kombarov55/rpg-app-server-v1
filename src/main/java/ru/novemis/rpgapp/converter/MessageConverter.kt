package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.conversation.Message
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.repository.conversation.ConversationRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import java.util.*

@Component
class MessageConverter(
        private val conversationRepository: ConversationRepository,
        private val userAccountRepository: UserAccountRepository
) {

    fun toDomain(conversationId: String, messageForm: MessageForm): Message {
        return messageForm.let {
            Message(
                    conversation = conversationRepository.findById(conversationId).orElseThrow { IllegalArgumentException() },
                    author = userAccountRepository.findByUserId(it.authorId) ?: throw IllegalArgumentException(),
                    creationDate = Date(),
                    text = it.text
            )
        }
    }

    fun toDto(message: Message): MessageDto {
        return message.let {
            MessageDto(
                    id = it.id,
                    authorUserId = it.author?.userId!!,
                    authorImgSrc = it.author?.photo50Url!!,
                    authorFullName = it.author?.let { it.firstName + " " + it.lastName }!!,
                    creationDate = it.creationDate.time,
                    text = it.text
            )
        }
    }

}