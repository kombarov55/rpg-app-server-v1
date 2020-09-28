package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.ItemForSaleConverter
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@Component
open class ShopService(
        private val repository: ShopRepository,
        private val converter: ShopConverter,

        private val itemForSaleConverter: ItemForSaleConverter,

        private val gameRepository: GameRepository
) {

    @Transactional
    open fun getAllByGameId(gameId: String): List<ShopDto> {
        return gameRepository.findById(gameId).get()
                .shops.map { converter.toDto(it) }
    }

    @Transactional
    open fun save(shopForm: ShopForm, gameId: String): ShopDto {
        return shopForm
                .let { converter.toDomain(it) }
                .let { repository.save(it) }
                .also { shop ->
                    gameRepository.findById(gameId).get()
                            .apply { shops += shop }
                            .let { gameRepository.save(it) }
                }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun update(form: ShopForm, gameId: String, shopId: String): ShopDto {
        return form
                .let { converter.toDomain(form) }
                .apply { id = shopId }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun delete(id: String, gameId: String): ShopDto {
        return repository.findById(id).orElseThrow { IllegalArgumentException() }
                .also {
                    gameRepository.findById(gameId).get()
                            .apply { shops = shops.filter { it.id !== id } }
                            .let { gameRepository.save(it) }
                }
                .also { repository.delete(it) }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun addItemForSale(gameId: String, shopId: String, form: ItemForSaleForm): ShopDto {
        return repository.findById(shopId).get()
                .apply { itemsForSale += itemForSaleConverter.toDomain(form, gameId) }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

}