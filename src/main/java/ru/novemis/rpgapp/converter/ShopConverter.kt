package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.GameRepository
import java.lang.IllegalArgumentException

@Component
class ShopConverter(
        private val gameRepository: GameRepository,
        private val itemForSaleConverter: ItemForSaleConverter
) {

    fun toDomain(form: ShopForm): Shop {
        return Shop(
                name = form.name,
                img = form.img,
                type = form.type
        )
    }

    fun toDto(domain: Shop): ShopDto {
        return ShopDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                type = domain.type!!,
                itemsForSale = domain.itemsForSale.map { itemForSaleConverter.toDto(it) }
        )
    }

}