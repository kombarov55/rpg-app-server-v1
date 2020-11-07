package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.converter.ItemForSaleConverter
import ru.novemis.rpgapp.dto.game.dto.GameDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.form.GameForm
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.ItemForSaleRepository
import ru.novemis.rpgapp.util.appendProtocol
import java.util.*
import javax.transaction.Transactional

@Component
open class GameService(
        private val converter: GameConverter,
        private val repository: GameRepository,

        private val itemForSaleRepository: ItemForSaleRepository,
        private val itemForSaleConverter: ItemForSaleConverter
) {

    @Transactional
    open fun getById(id: String): GameDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun findOpenGames(): List<GameDto> {
        return repository.findOpenGames().map { converter.toDto(it) }
    }

    @Transactional
    open fun findAllGames(): List<GameShortDto> {
        return repository.findAll().map { converter.toShortDto(it) }
    }

    @Transactional
    open fun save(networkId: String? = null, subnetworkId: String? = null, gameId: String? = null, form: GameForm): GameDto {
        return converter.toDto(
                repository.save(
                        converter.toDomain(gameId = gameId, networkId = networkId, subnetworkId = subnetworkId, form = form)
                )
        )
    }

    @Transactional
    open fun update(id: String, form: GameForm): GameDto {
        return repository.findById(id).get().apply {
            title = form.title
            description = form.description
            imgName = form.img
            backgroundName = form.background
            groupLink = appendProtocol(form.groupLink)
            disclaimerText = form.disclaimerText
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }

    @Transactional
    open fun findByNetworkId(id: String): List<GameDto> {
        return repository.findByNetworkId(id)
                .map { converter.toDto(it) }
    }

    @Transactional
    open fun findBySubnetworkId(id: String): List<GameDto> {
        return repository.findBySubnetworkId(id)
                .map { converter.toDto(it) }
    }

    @Transactional
    open fun updateByNetworkId(gameId: String, networkId: String, form: GameForm): GameDto {
        return converter.toDto(
                repository.save(
                        converter.toDomain(form = form, gameId = gameId, networkId = networkId)))
    }

    @Transactional
    open fun updateBySubnetwork(gameId: String, subnetworkId: String, form: GameForm): GameDto {
        return converter.toDto(
                repository.save(
                        converter.toDomain(form = form, gameId = gameId, subnetworkId = subnetworkId)))
    }

    @Transactional
    open fun delete(gameId: String) {
        val game = repository.findById(gameId).orElseThrow { IllegalArgumentException() }
        game.deleted = true
        game.deletionDate = Date()

        repository.save(game)
    }

    @Transactional
    open fun addItemForSale(gameId: String, form: ItemForSaleForm): GameDto {
        val game = repository.findById(gameId).get()
        val itemForSale = itemForSaleConverter.toDomain(form, gameId).apply {
            this.game = game
        }


        return repository.save(game)
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun removeItemForSale(gameId: String, itemForSaleId: String): GameDto {
        itemForSaleRepository.deleteById(itemForSaleId)

        return repository.findById(gameId).get()
                .apply {
//                    itemsForSale = itemsForSale.filter { it.id != itemForSaleId }
                }.let { repository.save(it) }
                .let { converter.toDto(it) }
    }
}