package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.dto.network.GameDto
import ru.novemis.rpgapp.dto.network.GameForm
import ru.novemis.rpgapp.repository.network.GameRepository
import java.util.*
import javax.transaction.Transactional

@Component
open class GameService(
        private val gameConverter: GameConverter,
        private val gameRepository: GameRepository
) {

    open fun save(form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(form)
                )
        )
    }

    open fun findByNetworkId(id: String): List<GameDto> {
        return gameRepository.findByNetworkId(id)
                .map { gameConverter.toDto(it) }
    }

    open fun findBySubnetworkId(id: String): List<GameDto> {
        return gameRepository.findBySubnetworkId(id)
                .map { gameConverter.toDto(it) }
    }

    @Transactional
    open fun delete(gameId: String) {
        val game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
        game.deleted = true
        game.deletionDate = Date()

        gameRepository.save(game)
    }


}