package ru.novemis.rpgapp.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.MessageConverter
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.repository.conversation.MessageRepository

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

    fun saveMessage(messageForm: MessageForm): MessageDto {
        return messageConverter.toDto(
                messageRepository.save(
                        messageConverter.toDomain(messageForm)))
    }

}