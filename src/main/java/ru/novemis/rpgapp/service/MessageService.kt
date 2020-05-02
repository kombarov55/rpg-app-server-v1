package ru.novemis.rpgapp.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.MessageConverter
import ru.novemis.rpgapp.domain.conversation.Message
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.repository.conversation.MessageRepository
import java.util.*

@Component
class MessageService(
        private val messageRepository: MessageRepository,
        private val messageConverter: MessageConverter
) {

    fun findByConversationId(conversationId: String, page: Int, pageSize: Int): List<MessageDto> {

        return messageRepository
                .findByConversationId(
                        conversationId,
                        PageRequest.of(page, pageSize, Sort.by("creationDate").descending()))
                .map { messageConverter.toDto(it) }
    }

    fun saveMessage(conversationId: String, messageForm: MessageForm): MessageDto {
        return messageConverter.toDto(
                messageRepository.save(
                        messageConverter.toDomain(conversationId, messageForm)))
    }

    fun pollMessages(conversationId: String, lastMsgTimestamp: Long, userId: Long?): List<MessageDto> {
        return pollMessagesFromDatabase(conversationId, lastMsgTimestamp)
//                .filter { it.author?.userId!! != userId }
                .map { messageConverter.toDto(it) }
    }

    fun pollMessagesFromDatabase(conversationId: String, lastMsgTimestamp: Long): List<Message> {
        val msgs = messageRepository
                .findByConversationIdAndAfterDate(
                        conversationId,
                        Date(lastMsgTimestamp),
                        Sort.by("creationDate").descending())

        if (msgs.isEmpty()) {
            Thread.sleep(1000)
            return pollMessagesFromDatabase(conversationId, lastMsgTimestamp)
        } else {
            return msgs
        }
    }

}