package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.network.Game
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.repository.network.NetworkRepository
import ru.novemis.rpgapp.repository.network.SubnetworkRepository

@Component
class GameConverter(
        private val networkRepository: NetworkRepository,
        private val subnetworkRepository: SubnetworkRepository
) {

    fun toDomain(form: GameForm): Game {
        return Game(
                title = form.title,
                description = form.description,
                imgSrc = form.imgSrc,
                network = form.networkId?.let { networkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() },
                subnetwork = form.subnetworkId?.let { subnetworkRepository.findById(it) }?.orElseThrow { IllegalArgumentException() }
        )
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