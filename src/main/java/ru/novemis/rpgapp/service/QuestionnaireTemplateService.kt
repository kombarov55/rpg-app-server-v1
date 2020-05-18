package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.QuestionnaireTemplateConverter
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateRepository
import javax.transaction.Transactional

@Component
open class QuestionnaireTemplateService(
        private val questionnaireTemplateConverter: QuestionnaireTemplateConverter,
        private val questionnaireTemplateRepository: QuestionnaireTemplateRepository
) {

    @Transactional
    open fun save(form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateConverter.toDomain(form)
                .let { questionnaireTemplateRepository.save(it) }
                .let { questionnaireTemplateConverter.toShortDto(it) }
    }

    @Transactional
    open fun findShortByGameId(gameId: String): List<QuestionnaireTemplateShortDto> {
        return questionnaireTemplateRepository.findByGameId(gameId).map { questionnaireTemplateConverter.toShortDto(it) }
    }

    @Transactional
    open fun update(id: String, form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateConverter.toDomain(form)
                .apply { this.id = id }
                .let { questionnaireTemplateRepository.save(it) }
                .let { questionnaireTemplateConverter.toShortDto(it) }
    }

    @Transactional
    open fun getById(id: String): QuestionnaireTemplateForm {
        return questionnaireTemplateRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .let { questionnaireTemplateConverter.toDto(it) }
    }

    @Transactional
    open fun delete(id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { deleted = true }
                .let { questionnaireTemplateRepository.save(it) }
                .let { questionnaireTemplateConverter.toShortDto(it) }
    }

    @Transactional
    open fun restore(id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateRepository.findById(id).orElseThrow { IllegalArgumentException() }
                .apply { deleted = false }
                .let { questionnaireTemplateRepository.save(it) }
                .let { questionnaireTemplateConverter.toShortDto(it) }
    }

}