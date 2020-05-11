package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Currency
import ru.novemis.rpgapp.domain.network.Game
import ru.novemis.rpgapp.domain.network.SkillType
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.repository.network.CurrencyRepository
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.repository.network.SkillTypeRepository
import ru.novemis.rpgapp.repository.network.SubnetworkRepository
import java.util.*

@Component
class GameConverter(
        private val networkRepository: NetworkRepository,
        private val subnetworkRepository: SubnetworkRepository,
        private val currencyRepository: CurrencyRepository,
        private val skillTypeRepository: SkillTypeRepository
) {

    fun toDomain(form: GameForm, gameId: String? = null, networkId: String? = null, subnetworkId: String? = null): Game {
        return Game().apply {
            id = gameId ?: UUID.randomUUID().toString()
            title = form.title
            description = form.description
            imgSrc = form.imgSrc
            network = networkId?.let { networkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            subnetwork = subnetworkId?.let { subnetworkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
            currencies = form.currencies.map { name ->
                gameId?.let { gameId ->
                    currencyRepository.findByGameIdAndName(gameId, name) ?: Currency(name = name, game = this)
                } ?: Currency(name = name, game = this)
            }
            skillTypes = form.skillTypes.map { name ->
                gameId?.let { gameId ->
                    skillTypeRepository.findByGameIdAndName(gameId, name) ?: SkillType(name = name, game = this)
                } ?: SkillType(name = name, game = this)
            }
        }
    }

    fun toDto(game: Game): GameDto {
        return GameDto(
                id = game.id,
                title = game.title,
                description = game.description,
                imgSrc = "https://sun9-27.userapi.com/c857420/v857420029/1d203f/tKLlbcriafc.jpg"
        )
    }

}