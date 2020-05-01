package ru.novemis.rpgapp.repository.conversation

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import ru.novemis.rpgapp.domain.conversation.Message

interface MessageRepository : PagingAndSortingRepository<Message, String> {

    fun findByConversationId(conversationId: String, pageable: Pageable): List<Message>

}