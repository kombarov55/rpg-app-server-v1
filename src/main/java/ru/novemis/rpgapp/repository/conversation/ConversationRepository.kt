package ru.novemis.rpgapp.repository.conversation

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.conversation.Conversation

interface ConversationRepository : CrudRepository<Conversation, String> {

    @Query("select c from Conversation c " +
            "where " +
            "c.user1.id = :userId and c.user2.id = :companionId or " +
            "c.user1.id = :companionId and c.user2.id = :userId")
    fun findByAccountIds(userId: String, companionId: String): Conversation?
}