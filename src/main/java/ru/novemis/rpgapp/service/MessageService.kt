package ru.novemis.rpgapp.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.MessageConverter
import ru.novemis.rpgapp.domain.conversation.Message
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.repository.conversation.MessageRepository
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

@Component
class MessageService(
        private val messageRepository: MessageRepository,
        private val messageConverter: MessageConverter
) {

    private val conversationIdToPendingMessages: MutableMap<String, BlockingQueue<Message>> = mutableMapOf()

    fun findByConversationId(conversationId: String, page: Int, pageSize: Int): List<MessageDto> {

        return messageRepository
                .findByConversationId(
                        conversationId,
                        PageRequest.of(page, pageSize, Sort.by("creationDate").descending()))
                .map { messageConverter.toDto(it) }
    }

    fun saveMessage(messageForm: MessageForm): MessageDto {
        val message = messageConverter.toDomain(messageForm)

        putMessageInQueue(message)

        return messageConverter.toDto(
                messageRepository.save(
                        message))
    }

    fun pollMessages(conversationId: String): List<MessageDto> {
        val queue = conversationIdToPendingMessages[conversationId] ?: throw IllegalArgumentException()

        if (queue.size == 0) {
            return listOf(messageConverter.toDto(queue.take()))
        } else {
            val result = mutableListOf<Message>()
            queue.drainTo(result)

            return result.map { messageConverter.toDto(it) }
        }

    }

    private fun putMessageInQueue(message: Message) {
        val blockingQueue = conversationIdToPendingMessages[message.conversation?.id!!]
                ?: LinkedBlockingQueue<Message>().apply {
                    conversationIdToPendingMessages[message.conversation?.id!!] = this
                }

        blockingQueue.put(message)
    }

}