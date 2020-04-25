package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.http.VkRequests

@RestController
@RequestMapping("/user")
class UserController(
        private val vkRequests: VkRequests
) {

    @GetMapping("{user-id}")
    fun findById(@PathVariable("user-id") userId: String): String {
        return vkRequests.getUserInfo(userId)
    }

}