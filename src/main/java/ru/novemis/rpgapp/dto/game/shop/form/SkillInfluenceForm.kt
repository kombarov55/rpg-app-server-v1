package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.AriphmericModifier

data class SkillInfluenceForm(
        var skillId: String = "",
        var modifier: AriphmericModifier = AriphmericModifier.PLUS,
        var amount: Int = 0
)