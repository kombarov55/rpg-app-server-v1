package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterRoleDto
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto

@Component
class GameCharacterConverter(
    private val priceConverter: PriceCombinationConverter,
    private val spellConverter: SpellConverter,
    private val skillToLvlConverter: SkillToLvlConverter,
    private val organizationConverter: OrganizationConverter,
    private val gameConverter: GameConverter,
    private val itemConverter: ItemConverter,
    private val creditConverter: CreditConverter
) {

    fun toDto(domain: GameCharacter): GameCharacterDto {
        return GameCharacterDto(
            id = domain.id,
            name = domain.name,
            img = domain.img,
            fieldNameToValueList = domain.fieldToValueList.map { it.field!!.name to it.value }.toMap(),
            skillStats = domain.calculateStats(),
            balance = domain.balance!!.amounts.map { priceConverter.toDto(it) },
            learnedSpells = domain.learnedSpells.map { spellConverter.toDto(it) },
            learnedSkills = domain.learnedSkills.map { skillToLvlConverter.toDto(it) },
            balanceId = domain.balance!!.id,
            items = domain.items.map { itemConverter.toDto(it) },
            equippedItems = domain.equippedItems.map { itemConverter.toDto(it) },
            credits = domain.credits.filter { !it.isPaid }.map { creditConverter.toDto(it) },
            activityPoints = domain.activityPoints
        )
    }

    fun toShortDto(domain: GameCharacter): GameCharacterShortDto {
        return GameCharacterShortDto(
            id = domain.id,
            name = domain.name,
            img = domain.img,
            game = gameConverter.toShortDto(domain.game!!),
            country = organizationConverter.toShortDto(domain.country!!),
            status = domain.status,
            statusChangeDate = domain.statusChangeDate.time,
            balance = domain.balance!!.amounts.map { priceConverter.toDto(it) }
        )
    }

    fun toRoleDto(domain: GameCharacter): GameCharacterRoleDto {
        return GameCharacterRoleDto(
            id = domain.id,
            name = domain.name,
            role = domain.role.label
        )
    }
}