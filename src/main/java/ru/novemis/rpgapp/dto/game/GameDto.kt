package ru.novemis.rpgapp.dto.game

import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.skill.SkillDto

data class GameDto(
        var id: String,
        var title: String,
        var description: String,
        var imgSrc: String,
        var currencies: List<String>,
        var skillTypes: List<String>,
        var skills: List<SkillDto>,
        var questionnaireTemplates: List<QuestionnaireTemplateShortDto>
)