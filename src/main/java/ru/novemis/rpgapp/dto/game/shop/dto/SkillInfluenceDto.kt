package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.ArithmeticModifierDto

data class SkillInfluenceDto(
        val skillName: String,
        val modifier: ArithmeticModifierDto,
        val amount: Int
)