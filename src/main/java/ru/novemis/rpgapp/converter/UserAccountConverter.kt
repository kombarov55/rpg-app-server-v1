package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountPreferencesDto

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
                    )

            )
        }
    }

}