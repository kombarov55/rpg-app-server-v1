package ru.novemis.rpgapp.dto.game.questionnaire.dto

import ru.novemis.rpgapp.dto.game.skill.dto.SkillShortDto

data class SkillToLvlDto(
        val id: String,
        val skill: SkillShortDto,
        val amount: Int
)