package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.dto.game.shop.dto.WarehouseEntryDto
import ru.novemis.rpgapp.dto.game.shop.form.WarehouseEntryForm
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository

@Component
class WarehouseEntryConverter(
        private val itemTemplateRepository: ItemTemplateRepository,
        private val itemTemplateConverter: ItemTemplateConverter
) {

    fun toDomain(form: WarehouseEntryForm): WarehouseEntry {
        return WarehouseEntry(
                itemTemplate = itemTemplateRepository.findById(form.itemTemplate!!.id!!).orElseThrow { IllegalArgumentException() },
                amount = form.amount
        )
    }

    fun toDto(domain: WarehouseEntry): WarehouseEntryDto {
        return WarehouseEntryDto(
                id = domain.id,
                itemTemplate = itemTemplateConverter.toDto(domain.itemTemplate!!),
                amount = domain.amount
        )
    }
}