package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.http.VkRequests
import ru.novemis.rpgapp.model.useraccount.UserAccount
import ru.novemis.rpgapp.model.useraccount.UserAccountRole

@Component
class UserAccountService(
        private val vkRequests: VkRequests,
        private val userAccountRepository: UserAccountRepository
) {

    fun getAccountByUserId(userId: Int): UserAccount {
        return userAccountRepository.findByUserId(userId)
                ?: userAccountRepository.save(
                        vkRequests.getUserInfo(userId).apply {
                            role = UserAccountRole.VISITOR
                        }
                )

    }

}