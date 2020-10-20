package ru.novemis.rpgapp.repository.game.questionnaire

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire

interface QuestionnaireRepository : CrudRepository<Questionnaire, String> {

    @Query("select q from Questionnaire q where q.status <> 2 order by q.status")
    fun findAllByGameId(gameId: String): List<Questionnaire>
}