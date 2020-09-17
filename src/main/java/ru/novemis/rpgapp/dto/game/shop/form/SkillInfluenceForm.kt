package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.ArithmeticModifierForm
import ru.novemis.rpgapp.dto.game.skill.form.SkillForm

data class SkillInfluenceForm(
        var skill: SkillForm? = null,
        var modifier: ArithmeticModifierForm? = null,
        var amount: Int = 0
)