package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.dao.announcement.AnnouncementRepository
import ru.novemis.rpgapp.dao.useraccount.UserAccountRepository
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.domain.useraccount.UserAccountRole
import ru.novemis.rpgapp.dto.useraccount.UserAccountDto
import ru.novemis.rpgapp.http.VkRequests
import javax.transaction.Transactional

@Component
open class UserAccountService(
        private val vkRequests: VkRequests,
        private val userAccountRepository: UserAccountRepository,
        private val announcementRepository: AnnouncementRepository,
        private val userAccountConverter: UserAccountConverter
) {

    @Transactional
    open fun getAccountDtoByUserId(userId: Long): UserAccountDto {
        return userAccountConverter.toDto(getAccountByUserId(userId))
    }

    @Transactional
    open fun getAccountByUserId(userId: Long): UserAccount {
        return userAccountRepository.findByUserId(userId)
                ?: userAccountRepository.save(
                        vkRequests.getUserInfo(userId).apply {
                            role = UserAccountRole.VISITOR
                        }
                )

    }

    @Transactional
    open fun toggleFavoriteAnnouncement(userId: Long, announcementId: String): UserAccountDto {
       val userAccount = userAccountRepository.findByUserId(userId) ?: throw IllegalArgumentException()
        val announcement = announcementRepository.findById(announcementId).orElseThrow { IllegalArgumentException() }

        val favs = userAccount.userAccountPreferences.favoriteAnnouncements
        if (!favs.any { it.id == announcement.id }) {
            userAccount.userAccountPreferences.favoriteAnnouncements += announcement
        } else {
            userAccount.userAccountPreferences.favoriteAnnouncements = favs.filter { it.id != announcement.id }
        }

        return userAccountConverter.toDto(
                userAccountRepository.save(userAccount)
        )

    }
}