package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire.QuestionnaireStatus
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import java.util.*

@Component
class QuestionnaireService(
        private val repository: QuestionnaireRepository
) {

    fun changeStatus(id: String, newStatus: QuestionnaireStatus) {
        val questionnaire = repository.findById(id).get()
        questionnaire.status = newStatus
        questionnaire.statusChangeDate = Date()
        repository.save(questionnaire)
    }

}