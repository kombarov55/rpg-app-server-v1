package ru.novemis.rpgapp.repository.game.questionnaire_template

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate

interface QuestionnaireTemplateRepository : CrudRepository<QuestionnaireTemplate, String> {

    fun findAllByGameId(gameId: String): List<QuestionnaireTemplate>

}