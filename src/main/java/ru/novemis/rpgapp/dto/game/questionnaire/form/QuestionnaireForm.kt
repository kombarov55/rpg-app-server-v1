package ru.novemis.rpgapp.dto.game.questionnaire.form

import ru.novemis.rpgapp.dto.game.organization.form.OrganizationForm
import ru.novemis.rpgapp.dto.game.skill.form.SpellForm

data class QuestionnaireForm(
        var name: String,
        var fieldToValueList: List<FieldToValueForm> = mutableListOf(),
        var country: OrganizationForm? = null,
        var selectedSkillsToLvl: List<SkillToLvlForm> = mutableListOf(),
        var selectedSpells: List<SpellForm> = mutableListOf()
)