package ru.novemis.rpgapp.dto.game.questionnaire.dto

import ru.novemis.rpgapp.domain.game.questionnaire.QuestionnaireStatus
import ru.novemis.rpgapp.dto.game.organization.dto.OrganizationShortDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountShortDto

data class QuestionnaireDto(
        val id: String,
        val template: QuestionnaireTemplateShortDto,
        val fieldToValueList: List<FieldToValueDto>,
        val selectedSkillToLvlList: List<SkillToLvlDto>,
        val country: OrganizationShortDto,
        val selectedSpells: List<SpellDto>,
        val author: UserAccountShortDto,
        var creationDate: Long,
        var status: QuestionnaireStatus,
        var statusChangeDate: Long
)