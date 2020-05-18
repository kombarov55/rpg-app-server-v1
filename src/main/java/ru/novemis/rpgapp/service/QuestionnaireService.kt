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

    @Transactional
    open fun getById(id: String): QuestionnaireForm {
        return questionnaireRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { questionnaireConverter.toDto(it) }
    }

    @Transactional
    open fun delete(id: String): QuestionnaireShortDto {
        return questionnaireRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { deleted = true }
                .let { questionnaireRepository.save(it) }
                .let { questionnaireConverter.toShortDto(it) }
    }

    @Transactional
    open fun restore(id: String): QuestionnaireShortDto {
        return questionnaireRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { deleted = false }
                .let { questionnaireRepository.save(it) }
                .let { questionnaireConverter.toShortDto(it) }
    }

}