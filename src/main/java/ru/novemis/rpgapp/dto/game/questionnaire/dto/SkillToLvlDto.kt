package ru.novemis.rpgapp.dto.game.questionnaire.dto

import ru.novemis.rpgapp.dto.game.skill.dto.SkillDto

data class SkillToLvlDto(
        val id: String,
        val skill: SkillDto,
        val amount: Int
)