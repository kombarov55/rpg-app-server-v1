package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.conversation.ConversationDto
import ru.novemis.rpgapp.dto.conversation.ConversationForm
import ru.novemis.rpgapp.service.ConversationService

@RestController
@RequestMapping("/conversation")
class ConversationController(
        private val conversationService: ConversationService
) {

    @GetMapping("{user-id}")
    fun findAllConversations(@PathVariable("user-id") userId: Long): List<ConversationDto> {
        return conversationService.findByUserId(userId)
    }

    @PostMapping
    fun findOrCreateConversation(@RequestBody conversationForm: ConversationForm): ConversationDto {

        return conversationService.findOrCreateConversation(conversationForm)
    }

}