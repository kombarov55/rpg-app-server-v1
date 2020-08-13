package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.ArithmeticModifierForm

data class SkillInfluenceForm(
        var skillId: String = "",
        var modifier: ArithmeticModifierForm? = null,
        var amount: Int = 0
)