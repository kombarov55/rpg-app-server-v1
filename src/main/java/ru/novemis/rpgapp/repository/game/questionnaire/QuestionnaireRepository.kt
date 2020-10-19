package ru.novemis.rpgapp.repository.game.questionnaire

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire

interface QuestionnaireRepository : CrudRepository<Questionnaire, String> {

    fun findAllByGameId(gameId: String): List<Questionnaire>
}