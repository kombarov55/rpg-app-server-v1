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

    @Transactional
    open fun save(networkId: String? = null, subnetworkId: String? = null, form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(networkId =  networkId, subnetworkId = subnetworkId, form = form)
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

    open fun updateByNetworkId(gameId: String, networkId: String, form: GameForm): GameDto {
        return gameConverter.toDto(gameRepository.save(gameConverter.toDomain(form = form, gameId = gameId, networkId = networkId)))
    }

    open fun updateBySubnetwork(gameId: String, subnetworkId: String, form: GameForm): GameDto {
        return gameConverter.toDto(gameRepository.save(gameConverter.toDomain(form = form, gameId = gameId, subnetworkId = subnetworkId)))
    }

    @Transactional
    open fun delete(gameId: String) {
        val game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
        game.deleted = true
        game.deletionDate = Date()

        gameRepository.save(game)
    }


}