package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.conversation.MessageDto
import ru.novemis.rpgapp.dto.conversation.MessageForm
import ru.novemis.rpgapp.service.MessageService

@RestController
@RequestMapping("/conversation")
class MessageController(
        private val messageService: MessageService
) {

    @GetMapping("/{conversation-id}/message")
    fun findByConversationId(@PathVariable("conversation-id") conversationId: String,
                             @RequestParam("page") page: Int,
                             @RequestParam("pageSize") pageSize: Int): List<MessageDto> {
        return messageService.findByConversationId(conversationId, page, pageSize)
    }

    @PostMapping("/{conversation-id}/message")
    fun save(@PathVariable("conversation-id") conversationId: String,
             @RequestBody messageForm: MessageForm): MessageDto {
        return messageService.saveMessage(conversationId, messageForm)
    }

    @GetMapping("/{conversation-id}/message/longpoll")
    fun longpoll(@PathVariable("conversation-id") conversationId: String,
                 @RequestParam("lastMsgTimestamp") lastMsgTimestamp: Long,
                 @RequestParam(value = "myUserId", required = false) myUserId: Long?): List<MessageDto> {
        return messageService.pollMessages(conversationId, lastMsgTimestamp, myUserId)
    }

}