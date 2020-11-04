package ru.novemis.rpgapp.dto.game.character.dto

import ru.novemis.rpgapp.dto.game.common.dto.PriceDto
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.questionnaire.dto.SkillToLvlDto
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto

data class GameCharacterDto(
        val id: String,
        val name: String,
        val fieldNameToValueList: Map<String, String>,
        val balance: List<PriceDto>,
        val learnedSpells: List<SpellDto>,
        val learnedSkills: List<SkillToLvlDto>,
        val balanceId: String
)