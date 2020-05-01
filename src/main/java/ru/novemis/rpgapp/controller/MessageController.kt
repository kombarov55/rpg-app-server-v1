package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.service.MessageService

@RestController
@RequestMapping("/message")
class MessageController(
        private val messageService: MessageService
) {

    @GetMapping
    fun findByConversationId(@RequestParam("conversationId") conversationId: String,
                             @RequestParam("page") page: Int,
                             @RequestParam("pageSize") pageSize: Int): List<MessageDto> {
        return messageService.findByConversationId(conversationId, page, pageSize)
    }

    @PostMapping
    fun save(@RequestBody messageForm: MessageForm): MessageDto {
        return messageService.saveMessage(messageForm)
    }

    @GetMapping("/{conversation-id}")
    fun longpoll(@PathVariable("conversation-id") conversationId: String): List<MessageDto> {
        return messageService.pollMessages(conversationId)
    }

}