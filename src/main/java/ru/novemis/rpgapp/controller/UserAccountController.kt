package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.model.useraccount.UserAccount
import ru.novemis.rpgapp.service.UserAccountService

@RestController
@RequestMapping("/user")
class UserAccountController(
        private val userAccountService: UserAccountService
) {

    @GetMapping("{user-id}")
    fun findById(@PathVariable("user-id") userId: Int): UserAccount = userAccountService.getAccountByUserId(userId)

}