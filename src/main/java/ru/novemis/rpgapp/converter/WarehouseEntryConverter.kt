package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import ru.novemis.rpgapp.repository.game.shop.ShopRepository

@Component
class WarehouseEntryConverter(
        private val merchandiseRepository: MerchandiseRepository,
        private val merchandiseConverter: MerchandiseConverter
) {

    fun toDomain(form: WarehouseEntryForm): WarehouseEntry {
        return WarehouseEntry(
                merchandise = merchandiseRepository.findById(form.merchandise!!.id!!).orElseThrow { IllegalArgumentException() },
                amount = form.amount
        )
    }

    fun toDto(domain: WarehouseEntry): WarehouseEntryDto {
        return WarehouseEntryDto(
                id = domain.id,
                merchandise = merchandiseConverter.toDto(domain.merchandise!!),
                amount = domain.amount
        )
    }
}