package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.dto.GameDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.game.form.GameForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.ItemForSaleRepository
import ru.novemis.rpgapp.util.appendProtocol
import java.util.*
import javax.transaction.Transactional

@Component
open class GameService(
        private val converter: GameConverter,
        private val gameRepository: GameRepository,
        private val priceConverter: PriceCombinationConverter,
        private val itemForSaleRepository: ItemForSaleRepository,
        private val characterRepository: GameCharacterRepository,
        private val balanceService: BalanceService
) {

    @Transactional
    open fun getById(id: String): GameDto {
        return gameRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun findOpenGames(): List<GameDto> {
        return gameRepository.findOpenGames().map { converter.toDto(it) }
    }

    @Transactional
    open fun findAllGames(): List<GameShortDto> {
        return gameRepository.findAll().map { converter.toShortDto(it) }
    }

    @Transactional
    open fun save(networkId: String? = null, subnetworkId: String? = null, gameId: String? = null, form: GameForm): GameDto {
        return converter.toDto(
                gameRepository.save(
                        converter.toDomain(gameId = gameId, networkId = networkId, subnetworkId = subnetworkId, form = form)
                )
        )
    }

    @Transactional
    open fun update(id: String, form: GameForm): GameDto {
        return gameRepository.findById(id).get().apply {
            title = form.title
            description = form.description
            imgName = form.img
            backgroundName = form.background
            groupLink = appendProtocol(form.groupLink)
            disclaimerText = form.disclaimerText
        }.let { gameRepository.save(it) }.let { converter.toDto(it) }
    }

    @Transactional
    open fun findByNetworkId(id: String): List<GameDto> {
        return gameRepository.findByNetworkId(id)
                .map { converter.toDto(it) }
    }

    @Transactional
    open fun findBySubnetworkId(id: String): List<GameDto> {
        return gameRepository.findBySubnetworkId(id)
                .map { converter.toDto(it) }
    }

    @Transactional
    open fun updateByNetworkId(gameId: String, networkId: String, form: GameForm): GameDto {
        return converter.toDto(
                gameRepository.save(
                        converter.toDomain(form = form, gameId = gameId, networkId = networkId)))
    }

    @Transactional
    open fun updateBySubnetwork(gameId: String, subnetworkId: String, form: GameForm): GameDto {
        return converter.toDto(
                gameRepository.save(
                        converter.toDomain(form = form, gameId = gameId, subnetworkId = subnetworkId)))
    }

    @Transactional
    open fun delete(gameId: String) {
        val game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException() }
        game.deleted = true
        game.deletionDate = Date()

        gameRepository.save(game)
    }

    @Transactional
    open fun addItemForSale(gameId: String, merchandiseId: String, price: List<PriceForm>) {
        val game = gameRepository.findById(gameId).get()
        game.itemsForSale += ItemForSale(
                merchandise = Merchandise(merchandiseId),
                price = priceConverter.toDomain(price, gameId),
                game = game
        )
        gameRepository.save(game)
    }

    @Transactional
    open fun purchaseItem(itemForSaleId: String, gameId: String, characterId: String, balanceId: String) {
        val itemForSale = itemForSaleRepository.findById(itemForSaleId).get()
        val character = characterRepository.findById(characterId).get()
        val game = gameRepository.findById(gameId).get()

        itemForSale.price!!.prices.forEach { amount -> balanceService.subtract(game.id, balanceId, amount.currency!!.name, amount.amount) }
        itemForSale.game = null

        game.itemsForSale = game.itemsForSale.filter { it.id != itemForSaleId }
        character.ownedMerchandise += itemForSale.cloneMerchandise()

        gameRepository.save(game)
        characterRepository.save(character)
    }
}