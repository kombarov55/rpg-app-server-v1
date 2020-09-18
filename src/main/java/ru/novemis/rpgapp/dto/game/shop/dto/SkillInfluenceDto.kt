package ru.novemis.rpgapp.dto.game.shop.dto

import ru.novemis.rpgapp.domain.game.shop.ArithmeticModifierDto
import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class SkillInfluenceDto(
        val skill: SkillShortDto,
        val modifier: ArithmeticModifierDto,
        val amount: Int
)