package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.conversation.ConversationDto
import ru.novemis.rpgapp.service.ConversationService

@RestController
@RequestMapping("/conversation")
class ConversationController(
        private val conversationService: ConversationService
) {

    @GetMapping
    fun findOrCreateConversation(@RequestParam("userId") userId: Long,
                                 @RequestParam("companionId") companionId: Long): ConversationDto {

        return conversationService.findOrCreateConversation(userId, companionId)
    }

}