package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.dto.game.GameDto
import ru.novemis.rpgapp.dto.game.GameForm
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import ru.novemis.rpgapp.util.appendProtocol
import java.util.UUID

@Component
class GameConverter(
        private val networkRepository: NetworkRepository,
        private val subnetworkRepository: SubnetworkRepository,
        private val currencyConverter: CurrencyConverter,
        private val skillCategoryConverter: SkillCategoryConverter
) {

    fun toDomain(form: GameForm, gameId: String? = null, networkId: String? = null, subnetworkId: String? = null): Game {
        return Game().apply {
            val thatGame = this

            id = gameId ?: UUID.randomUUID().toString()
            title = form.title
            description = form.description
            imgName = form.img
            backgroundName = form.background
            groupLink = appendProtocol(form.groupLink)
            network = networkId?.let { networkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            subnetwork = subnetworkId?.let { subnetworkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            currencies = form.currencies.map { currencyForm -> currencyConverter.toDomainOrExisting(thatGame, currencyForm) }
        }
    }

    fun toDto(game: Game): GameDto {
        return GameDto(
                id = game.id,
                title = game.title,
                description = game.description,
                imgSrc = game.imgName,
                backgroundImgSrc = game.backgroundName,
                groupLink = game.groupLink,
                currencies = game.currencies.map { currency -> currencyConverter.toDto(currency) },
                skillCategories = game.skillCategories.map {skillCategoryConverter.toDto(it) }
        )
    }

}