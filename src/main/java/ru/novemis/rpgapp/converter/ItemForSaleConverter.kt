package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto

@Component
class ItemForSaleConverter(
        private val merchandiseConverter: MerchandiseConverter
) {

    fun toDto(domain: ItemForSale): ItemForSaleDto {
        return ItemForSaleDto(
                id = domain.id,
                merchandise = merchandiseConverter.toDto(domain.merchandise!!),
                amount = domain.amount
        )
    }

}