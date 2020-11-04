package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.useraccount.GameToActiveCharacter
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.domain.useraccount.UserAccountRole
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto
import ru.novemis.rpgapp.http.VkRequests
import ru.novemis.rpgapp.repository.announcement.AnnouncementRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import javax.transaction.Transactional

@Component
open class UserAccountService(
        private val repository: UserAccountRepository,
        private val converter: UserAccountConverter,
        private val vkRequests: VkRequests,
        private val announcementRepository: AnnouncementRepository
) {

    @Transactional
    open fun getAccountDtoByUserId(userId: Long): UserAccountDto {
        return converter.toDto(getAccountByUserId(userId))
    }

    @Transactional
    open fun getAccountByUserId(userId: Long): UserAccount {
        return repository.findByUserId(userId)
                ?: repository.save(
                        vkRequests.getUserInfo(userId).apply {
                            role = UserAccountRole.VISITOR
                        }
                )

    }

    @Transactional
    open fun toggleFavoriteAnnouncement(userId: Long, announcementId: String): UserAccountDto {
        val userAccount = repository.findByUserId(userId) ?: throw IllegalArgumentException()
        val announcement = announcementRepository.findById(announcementId).orElseThrow { IllegalArgumentException() }

        val favs = userAccount.userAccountPreferences.favoriteAnnouncements
        if (!favs.any { it.id == announcement.id }) {
            userAccount.userAccountPreferences.favoriteAnnouncements += announcement
        } else {
            userAccount.userAccountPreferences.favoriteAnnouncements = favs.filter { it.id != announcement.id }
        }

        return converter.toDto(
                repository.save(userAccount)
        )
    }

    @Transactional
    open fun toggleRespondAnnouncement(userId: Long, announcementId: String): UserAccountDto {
        val userAccount = repository.findByUserId(userId) ?: throw IllegalArgumentException()
        val announcement = announcementRepository.findById(announcementId).orElseThrow { IllegalArgumentException() }

        val respondedAnnouncements = userAccount.userAccountPreferences.respondedAnnouncements
        if (!respondedAnnouncements.any { it.id == announcement.id }) {
            userAccount.userAccountPreferences.respondedAnnouncements += announcement
        } else {
            userAccount.userAccountPreferences.respondedAnnouncements = respondedAnnouncements.filter { it.id != announcement.id }
        }

        return converter.toDto(
                repository.save(userAccount)
        )
    }

    @Transactional
    open fun findAll(): List<UserAccountDto> {
        return repository.findAll()
                .map { converter.toDto(it) }
    }

    open fun findAllShort(): List<UserAccountShortDto> {
        return repository.findAll()
                .map { converter.toShortDto(it) }
    }

    open fun setActiveCharacterForGame(userAccount: UserAccount, game: Game, character: GameCharacter) {
        val updatedEntity = userAccount.gameToActiveCharacter.find { it.game!!.id == game.id } ?: GameToActiveCharacter(
                game = game,
                userAccount = userAccount
        ).apply { this.character = character }

        userAccount.gameToActiveCharacter = userAccount.gameToActiveCharacter.filter { it.id != updatedEntity.id } + updatedEntity

        repository.save(userAccount)
    }

    open fun makeCharacterActive(userId: Long, characterId: String, gameId: String) {
        val userAccount = repository.findByUserId(userId)!!

        val updatedEntity = (userAccount.gameToActiveCharacter.find { it.game!!.id == gameId }
                ?: GameToActiveCharacter(game = Game(id = gameId)))
                .apply { character = GameCharacter(id = characterId) }

        userAccount.gameToActiveCharacter = userAccount.gameToActiveCharacter.filter { it.game!!.id != gameId } + updatedEntity

        repository.save(userAccount)
    }

    open fun removeActiveCharacter(userId: Long, gameId: String) {
        val userAccount = repository.findByUserId(userId)!!

        val updatedEntity = (userAccount.gameToActiveCharacter.find { it.game!!.id == gameId }
                ?: GameToActiveCharacter(game = Game(id = gameId)))
                .apply { character = null }

        userAccount.gameToActiveCharacter = userAccount.gameToActiveCharacter.filter { it.game!!.id != gameId } + updatedEntity

        repository.save(userAccount)
    }
}