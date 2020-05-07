package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.repository.network.GameRepository

@Component
class GameService(
        private val gameConverter: GameConverter,
        private val gameRepository: GameRepository
) {

    fun save(form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(form)
                )
        )
    }

    fun findByNetworkId(id: String): List<GameDto> {
        return gameRepository.findByNetworkId(id)
                .map { gameConverter.toDto(it) }
    }

    fun findBySubnetworkId(id: String): List<GameDto> {
        return gameRepository.findBySubnetworkId(id)
                .map { gameConverter.toDto(it) }
    }


}