package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SkillCategoryToPointsConverter
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.SkillCategoryToPointsDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.SkillCategoryToPointsForm
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.SkillCategoryToPointsRepository
import javax.transaction.Transactional

@RestController
open class SkillCategoryToPointsController(
        private val repository: SkillCategoryToPointsRepository,
        private val converter: SkillCategoryToPointsConverter,
        private val questionnaireTemplateRepository: QuestionnaireTemplateRepository
) {

    @PostMapping("/questionnaireTemplate/{id}/skillCategoryToPoints")
    @Transactional
    open fun save(
            @PathVariable("id") questionnaireTemplateId: String,
            @RequestBody form: SkillCategoryToPointsForm
    ): SkillCategoryToPointsDto {
        return converter.toDomain(form, questionnaireTemplateRepository.findById(questionnaireTemplateId).get())
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/skillCategoryToPoints/{id}")
    open fun delete(
            @PathVariable("id") id: String
    ): SkillCategoryToPointsDto {
        return repository.findById(id).get()
                .also {
                    repository.deleteById(id)
                }.let { converter.toDto(it) }
    }

}