package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountPreferencesDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

@Service
class UserAccountConverter {

    fun toDto(userAccount: UserAccount): UserAccountDto {
        return userAccount.let {
            UserAccountDto(
                    id = it.id,
                    userId = it.userId,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    photo50Url = it.photo50Url,
                    userAccountPreferences = UserAccountPreferencesDto(
                            favAnnouncementIds = it.userAccountPreferences.favoriteAnnouncements.map { it.id },
                            respondedAnnouncementIds = it.userAccountPreferences.respondedAnnouncements.map { it.id }
                    ),
                    gameIdToActiveCharacterId = userAccount.gameToActiveCharacter.map { it.game!!.id to it.character!!.id }.toMap()
            )
        }
    }

    fun toShortDto(domain: UserAccount): UserAccountShortDto {
        return UserAccountShortDto(
                id = domain.id,
                vkUserId = domain.userId,
                fullName = domain.firstName + " " + domain.lastName,
                img = domain.photo50Url,
                role = domain.role.label
        )
    }

}