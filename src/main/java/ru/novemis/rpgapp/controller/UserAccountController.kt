package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.http.VkRequests
import ru.novemis.rpgapp.model.useraccount.UserAccount

@RestController
@RequestMapping("/user")
class UserAccountController(
        private val vkRequests: VkRequests,
        private val userAccountRepository: UserAccountRepository
) {

    @GetMapping("{user-id}")
    fun findById(@PathVariable("user-id") userId: Int): UserAccount {
        val userAccount = userAccountRepository.findByUserId(userId)
                ?: userAccountRepository.save(vkRequests.getUserInfo(userId))

        return userAccount
    }

}