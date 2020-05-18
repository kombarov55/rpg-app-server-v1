package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.QuestionnaireConverter
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireShortDto
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import javax.transaction.Transactional

@Component
open class QuestionnaireService(
        private val questionnaireConverter: QuestionnaireConverter,
        private val questionnaireRepository: QuestionnaireRepository
) {

    @Transactional
    open fun save(form: QuestionnaireForm): QuestionnaireShortDto {
        return questionnaireConverter.toDomain(form)
                .let { questionnaireRepository.save(it) }
                .let { questionnaireConverter.toShortDto(it) }
    }

    @Transactional
    open fun findShortByGameId(gameId: String): List<QuestionnaireShortDto> {
        return questionnaireRepository.findByGameId(gameId).map { questionnaireConverter.toShortDto(it) }
    }

    @Transactional
    open fun update(id: String, form: QuestionnaireForm): QuestionnaireShortDto {
        return questionnaireConverter.toDomain(form)
                .apply { this.id = id }
                .let { questionnaireRepository.save(it) }
                .let { questionnaireConverter.toShortDto(it) }
    }

}