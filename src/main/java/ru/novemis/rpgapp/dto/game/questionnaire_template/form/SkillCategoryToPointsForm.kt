package ru.novemis.rpgapp.dto.game.questionnaire_template.form

import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryShortForm

data class SkillCategoryToPointsForm(
        var skillCategory: SkillCategoryShortForm? = null,
        var amount: Int = 0
)