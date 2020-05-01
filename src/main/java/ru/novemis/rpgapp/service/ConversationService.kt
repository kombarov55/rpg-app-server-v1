package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ConversationConverter
import ru.novemis.rpgapp.domain.conversation.Conversation
import ru.novemis.rpgapp.dto.conversation.ConversationDto
import ru.novemis.rpgapp.repository.conversation.ConversationRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository

@Component
class ConversationService(
        private val userAccountRepository: UserAccountRepository,
        private val conversationRepository: ConversationRepository,
        private val conversationConverter: ConversationConverter
) {

    fun findOrCreateConversation(userId: Long, companionUserId: Long): ConversationDto {
        val author = userAccountRepository.findByUserId(userId) ?: throw IllegalArgumentException()
        val companion = userAccountRepository.findByUserId(companionUserId)

        val authorAccountId = author.id
        val companionAccountId = companion?.id ?: throw IllegalArgumentException()

        val conversation = conversationRepository.findByAccountIds(authorAccountId, companionAccountId) ?:
                conversationRepository.save(
                        Conversation(
                                user1 = author,
                                user2 = companion
                        )
                )

        return conversationConverter.toDto(author, conversation)
    }

}