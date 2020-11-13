package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.domain.game.shop.ItemForSaleOwner
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto

@Component
class ItemForSaleConverter(
        private val itemConverter: ItemConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDto(domain: ItemForSale): ItemForSaleDto {
        return ItemForSaleDto(
                id = domain.id,
                item = itemConverter.toShortDto(domain.item!!),
                price = priceCombinationConverter.toDto(domain.price!!),
                creationDate = domain.creationDate,
                ownerBalanceId = if (domain.ownerType == ItemForSaleOwner.CHARACTER) domain.owner!!.balance!!.id else null,
                ownerName = if (domain.ownerType == ItemForSaleOwner.CHARACTER) domain.owner!!.name else null
        )
    }
}