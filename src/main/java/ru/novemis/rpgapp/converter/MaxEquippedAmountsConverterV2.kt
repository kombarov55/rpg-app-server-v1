package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import ru.novemis.rpgapp.domain.game.MaxEquippedAmount
import ru.novemis.rpgapp.dto.game.dto.MaxEquippedAmountDto

@Mapper
interface MaxEquippedAmountsConverterV2 {
    fun toDto(domain: MaxEquippedAmount): MaxEquippedAmountDto
}