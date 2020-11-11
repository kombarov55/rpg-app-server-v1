package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.domain.game.shop.ItemForSaleOwner
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository

@Component
class ItemForSaleConverter(
        private val merchandiseConverter: MerchandiseConverter,
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDto(domain: ItemForSale): ItemForSaleDto {
        return ItemForSaleDto(
                id = domain.id,
                merchandise = merchandiseConverter.toShortDto(domain.merchandise!!),
                price = priceCombinationConverter.toDto(domain.price!!),
                creationDate = domain.creationDate,
                ownerBalanceId = if (domain.ownerType == ItemForSaleOwner.CHARACTER) domain.owner!!.balance!!.id else null,
                ownerName = if (domain.ownerType == ItemForSaleOwner.CHARACTER) domain.owner!!.name else null
        )
    }
}