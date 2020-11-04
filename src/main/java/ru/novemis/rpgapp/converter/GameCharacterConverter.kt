package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto

@Component
class GameCharacterConverter(
        private val priceConverter: PriceCombinationConverter,
        private val spellConverter: SpellConverter,
        private val skillToLvlConverter: SkillToLvlConverter,
        private val organizationConverter: OrganizationConverter,
        private val gameConverter: GameConverter
) {

    fun toDto(domain: GameCharacter): GameCharacterDto {
        return GameCharacterDto(
                id = domain.id,
                name = domain.name,
                fieldNameToValueList = domain.fieldToValueList.map { it.field!!.name to it.value }.toMap(),
                balance = domain.balance!!.amounts.map { priceConverter.toDto(it) },
                learnedSpells = domain.learnedSpells.map { spellConverter.toDto(it) },
                learnedSkills = domain.learnedSkills.map { skillToLvlConverter.toDto(it) },
                balanceId = domain.balance!!.id,
                managingOrganizations = domain.managingOrganizations.map { organizationConverter.toShortDto(it) }
        )
    }

    fun toShortDto(domain: GameCharacter): GameCharacterShortDto {
        return GameCharacterShortDto(
                id = domain.id,
                name = domain.name,
                game = gameConverter.toShortDto(domain.game!!),
                country = organizationConverter.toShortDto(domain.country!!),
                status = domain.status,
                statusChangeDate = domain.statusChangeDate.time
        )
    }
}