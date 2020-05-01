package ru.novemis.rpgapp.repository.conversation

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.conversation.Message

interface MessageRepository : CrudRepository<Message, String> {
}