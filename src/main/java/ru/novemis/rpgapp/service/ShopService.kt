package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.domain.game.shop.ItemForSaleOwner
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.ItemForSaleRepository
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val shopRepository: ShopRepository,
        private val converter: ShopConverter,
        private val gameRepository: GameRepository,
        private val characterRepository: GameCharacterRepository,
        private val itemTemplateRepository: ItemTemplateRepository,
        private val itemForSaleRepository: ItemForSaleRepository,
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
    open fun transferItemFromGame(gameId: String, destinationCharacterId: String, itemTemplateId: String) {
        val game = gameRepository.findById(gameId).get()
        gameRepository.save(game)

        val character = characterRepository.findById(destinationCharacterId).get()
        val itemTemplate = itemTemplateRepository.findById(itemTemplateId).get()
//        character.ownedMerchandise = character.ownedMerchandise + merchandise
        characterRepository.save(character)
    }

    @Transactional
    open fun setItemForSale(itemTemplateId: String, shopId: String, publisherId: String, price: List<PriceForm>) {
        val itemTemplate = itemTemplateRepository.findById(itemTemplateId).get()
        val shop = shopRepository.findById(shopId).get()
        val publisher = characterRepository.findById(publisherId).get()

        shop.itemsForSale += ItemForSale(
                itemTemplate = itemTemplate,
                price = priceCombinationConverter.toDomain(price, shop.organization!!.game!!.id),
                shop = shop,
                ownerType = ItemForSaleOwner.CHARACTER,
                owner = publisher
        )
        shopRepository.save(shop)

//        publisher.ownedMerchandise.filter { it.id !== merchandiseId }
        characterRepository.save(publisher)
    }

    @Transactional
    open fun purchase(shopId: String, buyerBalanceId: String, buyerCharacterId: String, gameId: String, itemForSaleId: String) {
        val shop = shopRepository.findById(shopId).get()
        val itemForSale = itemForSaleRepository.findById(itemForSaleId).get()
        val buyer = characterRepository.findById(buyerCharacterId).get()

        itemForSale.price!!.prices.forEach { amount -> balanceService.transfer(gameId, buyerBalanceId, itemForSale.owner!!.balance!!.id, amount.currency!!.name, amount.amount) }

        shop.itemsForSale = shop.itemsForSale.filter {it.id != itemForSaleId }
        itemForSale.shop = null
//        buyer.ownedMerchandise += itemForSale.merchandise!!

        shopRepository.save(shop)
        itemForSaleRepository.delete(itemForSale)
        characterRepository.save(buyer)
    }


}