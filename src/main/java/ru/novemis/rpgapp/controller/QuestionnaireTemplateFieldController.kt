package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.QuestionnaireTemplateFieldConverter
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateFieldDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.QuestionnaireTemplateFieldForm
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateFieldRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateRepository
import javax.transaction.Transactional

@RestController
open class QuestionnaireTemplateFieldController(
        private val repository: QuestionnaireTemplateFieldRepository,
        private val converter: QuestionnaireTemplateFieldConverter,

        private val questionnaireTemplateRepository: QuestionnaireTemplateRepository
) {

    @PostMapping("/questionnaireTemplate/{id}/field")
    @Transactional
    open fun save(
            @PathVariable("id") questionnaireTemplateId: String,
            @RequestBody form: QuestionnaireTemplateFieldForm
    ): QuestionnaireTemplateFieldDto {
        return converter.toDomain(form, questionnaireTemplateRepository.findById(questionnaireTemplateId).get())
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/field/{id}")
    @Transactional
    open fun edit(
            @PathVariable("id") id: String,
            @RequestBody form: QuestionnaireTemplateFieldForm
    ): QuestionnaireTemplateFieldDto {
        return repository.findById(id).get()
                .apply {
                    name = form.name
                    description = form.description
                    type = form.type
                    choicesDelimitedByComma = form.choices?.joinToString(",")
                }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/field/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): QuestionnaireTemplateFieldDto {
        return repository.findById(id).get()
                .also { repository.deleteById(id) }
                .let { converter.toDto(it) }
    }
}