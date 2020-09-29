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
    open fun update(form: ShopForm, gameId: String, shopId: String): ShopDto {
        return form
                .let { converter.toDomain(form) }
                .apply { id = shopId }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @Transactional
    open fun addItemForSale(gameId: String, shopId: String, form: ItemForSaleForm): ShopDto {
        return repository.findById(shopId).get()
                .apply {
                    val shop = this
                    itemsForSale += itemForSaleConverter.toDomain(form, gameId).apply { this.shop = shop }
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

}