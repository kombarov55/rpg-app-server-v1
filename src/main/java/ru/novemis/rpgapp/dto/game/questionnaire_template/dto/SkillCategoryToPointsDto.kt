package ru.novemis.rpgapp.dto.game.questionnaire_template.dto

import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryShortDto

data class SkillCategoryToPointsDto(
        val id: String,
        val skillCategory: SkillCategoryShortDto,
        val amount: Int
)