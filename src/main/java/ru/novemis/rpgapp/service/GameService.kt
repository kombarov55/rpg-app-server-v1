package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.converter.ItemForSaleConverter
import ru.novemis.rpgapp.dto.game.dto.GameDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.form.GameForm
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.repository.game.GameRepository
import java.util.*
import javax.transaction.Transactional

@Component
open class GameService(
        private val gameConverter: GameConverter,
        private val gameRepository: GameRepository,

        private val itemForSaleConverter: ItemForSaleConverter
) {

    @Transactional
    open fun getById(id: String): GameDto {
        return gameRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { gameConverter.toDto(it) }
    }

    @Transactional
    open fun findOpenGames(): List<GameDto> {
        return gameRepository.findOpenGames().map { gameConverter.toDto(it) }
    }

    @Transactional
    open fun findAllGames(): List<GameShortDto> {
        return gameRepository.findAll().map { gameConverter.toShortDto(it) }
    }

    @Transactional
    open fun save(networkId: String? = null, subnetworkId: String? = null, gameId: String? = null, form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(gameId = gameId, networkId = networkId, subnetworkId = subnetworkId, form = form)
                )
        )
    }

    @Transactional
    open fun findByNetworkId(id: String): List<GameDto> {
        return gameRepository.findByNetworkId(id)
                .map { gameConverter.toDto(it) }
    }

    @Transactional
    open fun findBySubnetworkId(id: String): List<GameDto> {
        return gameRepository.findBySubnetworkId(id)
                .map { gameConverter.toDto(it) }
    }

    @Transactional
    open fun updateByNetworkId(gameId: String, networkId: String, form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(form = form, gameId = gameId, networkId = networkId)))
    }

    @Transactional
    open fun updateBySubnetwork(gameId: String, subnetworkId: String, form: GameForm): GameDto {
        return gameConverter.toDto(
                gameRepository.save(
                        gameConverter.toDomain(form = form, gameId = gameId, subnetworkId = subnetworkId)))
    }

    @Transactional
    open fun delete(gameId: String) {
        val game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
        game.deleted = true
        game.deletionDate = Date()

        gameRepository.save(game)
    }

    @Transactional
    open fun addItemForSale(gameId: String, form: ItemForSaleForm): GameDto {
        val game = gameRepository.findById(gameId).get()
        val itemForSale = itemForSaleConverter.toDomain(form, gameId)

        game.itemsForSale += itemForSale

        return gameRepository.save(game)
                .let { gameConverter.toDto(it) }
    }

    @Transactional
    open fun removeItemForSale(gameId: String, itemForSaleId: String): GameDto {
        return gameRepository.findById(gameId).get()
                .apply {
                    itemsForSale = itemsForSale.filter { it.id != itemForSaleId }
                }.let { gameRepository.save(it) }
                .let { gameConverter.toDto(it) }
    }


}