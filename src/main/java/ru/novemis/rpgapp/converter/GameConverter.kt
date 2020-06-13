package ru.novemis.rpgapp.converter

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.skill.SkillType
import ru.novemis.rpgapp.dto.game.GameDto
import ru.novemis.rpgapp.dto.game.GameForm
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import ru.novemis.rpgapp.util.appendProtocol
import java.util.*

@Component
class GameConverter(
        @Value("\${imgPrefix}")
        private val imgPrefix: String,

        private val networkRepository: NetworkRepository,
        private val subnetworkRepository: SubnetworkRepository,
        private val skillTypeRepository: SkillTypeRepository,
        private val skillConverter: SkillConverter,
        private val questionnaireTemplateConverter: QuestionnaireTemplateConverter,
        private val currencyConverter: CurrencyConverter
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
            currencies = form.currencies.map { currencyForm -> currencyConverter.toDomain(thatGame, currencyForm) }

            skillTypes = form.skillTypes.map { name ->
                gameId?.let { gameId ->
                    skillTypeRepository.findByGameIdAndName(gameId, name) ?: SkillType(name = name, game = thatGame)
                } ?: SkillType(name = name, game = thatGame)
            }
        }
    }

    fun toDto(game: Game): GameDto {
        return GameDto(
                id = game.id,
                title = game.title,
                description = game.description,
                imgSrc = imgPrefix + "/" + game.imgName,
                backgroundImgSrc = imgPrefix + "/" + game.backgroundName,
                groupLink = game.groupLink,
                currencies = game.currencies.map { currency -> currencyConverter.toDto(currency)},
                skillTypes = game.skillTypes.map { it.name },
                skills = game.skills.map { skillConverter.toDto(it) },
                questionnaireTemplates = game.questionnaireTemplates.filter { !it.deleted }.map { questionnaireTemplateConverter.toShortDto(it) }
        )
    }

}