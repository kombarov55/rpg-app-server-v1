package ru.novemis.rpgapp.repository.game.questionnaire_template

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate

interface QuestionnaireTemplateRepository : CrudRepository<QuestionnaireTemplate, String> {

    @Query("select q from QuestionnaireTemplate q where q.game.id = :gameId and q.deleted = false")
    fun findByGameId(gameId: String): List<QuestionnaireTemplate>

}