package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto
import ru.novemis.rpgapp.dto.useraccount.form.ToggleFavoriteAnnouncementForm
import ru.novemis.rpgapp.dto.useraccount.form.ToggleRespondAnnouncementForm
import ru.novemis.rpgapp.service.UserAccountService

@RestController
@RequestMapping("/user")
class UserAccountController(
        private val userAccountService: UserAccountService
) {

    @GetMapping
    fun findAll(): List<UserAccountDto> {
        return userAccountService.findAll()
    }

    @GetMapping("/short")
    fun findAllShort(): List<UserAccountShortDto> {
        return userAccountService.findAllShort()
    }

    @GetMapping("/{user-id}")
    fun findById(@PathVariable("user-id") userId: Long): UserAccountDto {
        return userAccountService.getAccountDtoByUserId(userId)
    }

    @PatchMapping("/{user-id}/toggleFavAnnouncement")
    fun toggleFavoriteAnnouncement(@PathVariable("user-id") userId: Long,
                                   @RequestBody form: ToggleFavoriteAnnouncementForm): UserAccountDto {
        return userAccountService.toggleFavoriteAnnouncement(userId, form.announcementId)
    }

    @PatchMapping("{user-id}/toggleRespAnnouncement")
    fun toggleRespondAnnouncement(@PathVariable("user-id") userId: Long,
                                   @RequestBody form: ToggleRespondAnnouncementForm): UserAccountDto {
        return userAccountService.toggleRespondAnnouncement(userId, form.announcementId)
    }

}