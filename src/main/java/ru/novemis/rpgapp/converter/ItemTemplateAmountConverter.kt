package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.crafting.ItemTemplateAmount
import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.dto.game.crafting.dto.ItemTemplateAmountDto
import ru.novemis.rpgapp.dto.game.crafting.form.ItemTemplateAmountForm
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository

@Component
class ItemTemplateAmountConverter(
        private val itemTemplateRepository: ItemTemplateRepository,
        private val itemTemplateConverter: ItemTemplateConverter
) {

    fun toDomain(form: ItemTemplateAmountForm, recipe: Recipe): ItemTemplateAmount {
        return ItemTemplateAmount(
                itemTemplate = itemTemplateRepository.findById(form.itemTemplate!!.id).get(),
                amount = form.amount,
                recipe = recipe
        )
    }

    fun toDto(domain: ItemTemplateAmount): ItemTemplateAmountDto {
        return ItemTemplateAmountDto(
                id = domain.id,
                itemTemplate = itemTemplateConverter.toShortDto(domain.itemTemplate!!),
                amount = domain.amount
        )
    }
}