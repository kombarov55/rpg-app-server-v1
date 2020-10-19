package ru.novemis.rpgapp.dto.game.questionnaire.dto

import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

data class QuestionnaireDto(
        val id: String,
        val fieldToValueList: List<FieldToValueDto>,
        val selectedSkillToLvlList: List<SkillToLvlDto>,
        val country: OrganizationShortDto,
        val selectedSpells: List<SpellDto>,
        val author: UserAccountShortDto
)