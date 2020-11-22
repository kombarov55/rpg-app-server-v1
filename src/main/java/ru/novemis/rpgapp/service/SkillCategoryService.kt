package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.domain.game.skill.Spell

@Service
open class SkillCategoryService {

    fun getPricesForSpell(spell: Spell, learnedSpells: List<Spell>): List<PriceCombination> {
        val purchaseOptions = spell.schoolLvl!!.spellPurchaseOptions

        val exactPurchaseOption = purchaseOptions.find { purchaseOption ->
            val amountOfLearnedSpellsOnThatLevel = learnedSpells
                    .filter { it.schoolLvl!!.id == spell.schoolLvl!!.id }
                    .size

            purchaseOption.spellCount == amountOfLearnedSpellsOnThatLevel
        }

        return exactPurchaseOption?.priceCombinations
                ?: purchaseOptions.maxBy { it.spellCount }?.priceCombinations
                ?: emptyList()
    }

}