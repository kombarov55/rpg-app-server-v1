package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.useraccount.AddFavoriteAnnouncementForm
import ru.novemis.rpgapp.dto.useraccount.UserAccountDto
import ru.novemis.rpgapp.service.UserAccountService

@RestController
@RequestMapping("/user")
class UserAccountController(
        private val userAccountService: UserAccountService
) {

    @GetMapping("/{user-id}")
    fun findById(@PathVariable("user-id") userId: Long): UserAccountDto {
        return userAccountService.getAccountDtoByUserId(userId)
    }

    @PatchMapping("/{user-id}")
    fun addFavoriteAnnouncement(@PathVariable("user-id") userId: Long,
                                @RequestBody form: AddFavoriteAnnouncementForm): UserAccountDto {
        return userAccountService.addFavoriteAnnouncement(userId, form.announcementId)
    }

}