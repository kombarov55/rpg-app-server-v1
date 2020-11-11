package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val shopRepository: ShopRepository,
        private val converter: ShopConverter,
        private val gameRepository: GameRepository,
        private val characterRepository: GameCharacterRepository,
        private val merchandiseRepository: MerchandiseRepository,
        private val balanceService: BalanceService,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    @Transactional
    open fun update(form: ShopForm, gameId: String, shopId: String): ShopDto {
        return form
                .let { converter.toDomain(form) }
                .apply { id = shopId }
                .let { shopRepository.save(it) }
                .let { converter.toDto(it) }
    }


    @Transactional
    open fun transferItemFromGame(gameId: String, destinationCharacterId: String, merchandiseId: String) {
        val game = gameRepository.findById(gameId).get()
        gameRepository.save(game)

        val character = characterRepository.findById(destinationCharacterId).get()
        val merchandise = merchandiseRepository.findById(merchandiseId).get()
        character.ownedMerchandise = character.ownedMerchandise + merchandise
        characterRepository.save(character)
    }

    @Transactional
    open fun setItemForSale(merchandiseId: String, shopId: String, publisherId: String, price: List<PriceForm>) {
        val merchandise = merchandiseRepository.findById(merchandiseId).get()
        val shop = shopRepository.findById(shopId).get()
        val publisher = characterRepository.findById(publisherId).get()

        shop.itemsForSale += ItemForSale(
                merchandise = merchandise,
                price = priceCombinationConverter.toDomain(price, shop.organization!!.game!!.id),
                shop = shop
        )
        shopRepository.save(shop)

        publisher.ownedMerchandise.filter { it.id !== merchandiseId }
        characterRepository.save(publisher)
    }


}