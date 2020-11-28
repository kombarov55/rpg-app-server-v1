package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.MaxEquippedAmount
import ru.novemis.rpgapp.dto.game.dto.MaxEquippedAmountDto
import ru.novemis.rpgapp.dto.game.shop.dto.ItemCategoryDto

@Component
class MaxEquippedAmountsConverter {

    fun toDto(domain: MaxEquippedAmount): MaxEquippedAmountDto {
        return MaxEquippedAmountDto(
                id = domain.id,
                itemCategory = ItemCategoryDto(
                        id = domain.itemCategory!!.id,
                        name = domain.itemCategory!!.name
                ),
                amount = domain.amount
        )
    }

}