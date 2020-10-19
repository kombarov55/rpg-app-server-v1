package ru.novemis.rpgapp.dto.game.questionnaire.form

import ru.novemis.rpgapp.dto.game.skill.form.SkillForm

data class SkillToLvlForm(
        var skill: SkillForm? = null,
        var amount: Int = 0
)