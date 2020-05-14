package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.QuestionnaireConverter
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import javax.transaction.Transactional

@Component
open class QuestionnaireService(
        private val questionnaireConverter: QuestionnaireConverter,
        private val questionnaireRepository: QuestionnaireRepository
) {

    @Transactional
    open fun save(form: QuestionnaireForm) {
        questionnaireConverter.toDomain(form).let { questionnaireRepository.save(it) }
    }

}