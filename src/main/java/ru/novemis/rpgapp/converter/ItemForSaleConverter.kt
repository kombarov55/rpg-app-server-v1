package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.dto.game.shop.dto.ItemForSaleDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemForSaleForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository

@Component
class ItemForSaleConverter(
        private val merchandiseConverter: MerchandiseConverter,
        private val priceCombinationConverter: PriceCombinationConverter,

        private val merchandiseRepository: MerchandiseRepository
) {


    fun toDomain(form: ItemForSaleForm, gameId: String): ItemForSale {
        return ItemForSale(
                merchandise = merchandiseRepository.findById(form.merchandise!!.id!!).get(),
                price = priceCombinationConverter.toDomain(form.price, gameId),
                amount = form.amount
        )
    }

    fun toDto(domain: ItemForSale): ItemForSaleDto {
        return ItemForSaleDto(
                id = domain.id,
                merchandise = merchandiseConverter.toDto(domain.merchandise!!),
                amount = domain.amount
        )
    }

}