package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterDto

@Component
class GameCharacterConverter(
        private val priceConverter: PriceCombinationConverter,
        private val spellConverter: SpellConverter,
        private val skillToLvlConverter: SkillToLvlConverter
) {

    fun toDto(domain: GameCharacter): GameCharacterDto {
        return GameCharacterDto(
                id = domain.id,
                fieldNameToValueList = domain.fieldToValueList.map { it.field!!.name to it.value }.toMap(),
                balance = domain.balance.map { priceConverter.toDto(it) },
                learnedSpells = domain.learnedSpells.map { spellConverter.toDto(it) },
                learnedSkills = domain.learnedSkills.map { skillToLvlConverter.toDto(it) }
        )
    }
}