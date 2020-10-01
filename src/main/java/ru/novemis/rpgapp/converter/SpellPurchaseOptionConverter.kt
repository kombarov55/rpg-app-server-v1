package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.SpellPurchaseOption
import ru.novemis.rpgapp.dto.game.skill.dto.SpellPurchaseOptionDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellPurchaseOptionForm

@Component
class SpellPurchaseOptionConverter(
        private val priceCombinationConverter: PriceCombinationConverter
) {

    fun toDomain(form: SpellPurchaseOptionForm, gameId: String): SpellPurchaseOption {
        return SpellPurchaseOption().apply {
            spellCount = form.spellCount
            priceCombinations = form.priceCombinations.map { priceCombinationConverter.toDomain(it, gameId) }
        }
    }

    fun toDto(domain: SpellPurchaseOption): SpellPurchaseOptionDto {
        return SpellPurchaseOptionDto(
                id = domain.id,
                spellCount = domain.spellCount,
                priceCombinations = domain.priceCombinations.map { priceCombinationConverter.toDto(it) }
        )
    }

}