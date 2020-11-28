package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountPreferencesDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

@Service
class UserAccountConverter(
        private val gameToActiveCharacterConverter: GameToActiveCharacterConverter
) {

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
                    gameToActiveCharacter = userAccount.gameToActiveCharacter.map { gameToActiveCharacterConverter.toDto(it) },
                    characters = userAccount.characters.map {
                        GameCharacterShortDto(
                                id = it.id,
                                name = it.name,
                                game = GameShortDto(id = it.game!!.id)
                        )
                    }
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