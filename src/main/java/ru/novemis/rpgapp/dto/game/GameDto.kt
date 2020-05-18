package ru.novemis.rpgapp.dto.game

import ru.novemis.rpgapp.dto.game.skill.SkillDto
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireShortDto

data class GameDto(
        var id: String,
        var title: String,
        var description: String,
        var imgSrc: String,
        var currencies: List<String>,
        var skillTypes: List<String>,
        var skills: List<SkillDto>,
        var questionnaires: List<QuestionnaireShortDto>
)