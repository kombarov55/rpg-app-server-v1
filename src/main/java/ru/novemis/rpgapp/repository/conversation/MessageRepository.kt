package ru.novemis.rpgapp.repository.conversation

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import ru.novemis.rpgapp.domain.conversation.Message
import java.util.*

interface MessageRepository : PagingAndSortingRepository<Message, String> {

    fun findByConversationId(conversationId: String, pageable: Pageable): List<Message>

    @Query("select m from Message m where m.conversation.id = :conversationId and m.creationDate > :lastMsgDate")
    fun findByConversationIdAndAfterDate(conversationId: String, lastMsgDate: Date, sort: Sort): List<Message>

}