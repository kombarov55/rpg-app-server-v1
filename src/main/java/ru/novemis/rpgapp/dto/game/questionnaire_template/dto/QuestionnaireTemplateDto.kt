package ru.novemis.rpgapp.dto.game.questionnaire_template.dto

data class QuestionnaireTemplateDto(
        val id: String,
        val name: String,
        val description: String,
        val img: String,
        val fields: List<QuestionnaireTemplateFieldDto>,
        val skillCategoryToPoints: List<SkillCategoryToPointsDto>
)