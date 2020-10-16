package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.ArithmeticModifierForm
import ru.novemis.rpgapp.dto.game.skill.form.SkillShortForm

data class SkillInfluenceForm(
        var skill: SkillShortForm? = null,
        var modifier: ArithmeticModifierForm? = null,
        var amount: Int = 0
)