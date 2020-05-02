package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ConversationConverter
import ru.novemis.rpgapp.domain.conversation.Conversation
import ru.novemis.rpgapp.dto.conversation.ConversationDto
import ru.novemis.rpgapp.dto.conversation.ConversationForm
import ru.novemis.rpgapp.repository.conversation.ConversationRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import javax.transaction.Transactional

@Component
open class ConversationService(
        private val userAccountRepository: UserAccountRepository,
        private val conversationRepository: ConversationRepository,
        private val conversationConverter: ConversationConverter
) {

    @Transactional
    open fun findOrCreateConversation(form: ConversationForm): ConversationDto {
        val author = userAccountRepository.findByUserId(form.userId) ?: throw java.lang.IllegalArgumentException()
        val companion = userAccountRepository.findByUserId(form.companionUserId)

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

    @Transactional
    open fun findAllByUserId(userId: Long): List<ConversationDto> {
        val author = userAccountRepository.findByUserId(userId)
        val authorId = author?.id ?: throw IllegalArgumentException()

        return conversationRepository.findAllByUserId(authorId)
                .map { conversationConverter.toDto(author, it) }
    }

}