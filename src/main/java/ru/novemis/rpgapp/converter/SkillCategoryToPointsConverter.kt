package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.SkillCategoryToPoints
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.SkillCategoryToPointsDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.SkillCategoryToPointsForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository

@Component
class SkillCategoryToPointsConverter(
        private val skillCategoryRepository: SkillCategoryRepository,
        private val skillCategoryConverter: SkillCategoryConverter
) {

    fun toDomain(form: SkillCategoryToPointsForm, questionnaireTemplate: QuestionnaireTemplate? = null): SkillCategoryToPoints {
        return SkillCategoryToPoints(
                skillCategory = skillCategoryRepository.findById(form.skillCategory.id!!).get(),
                amount = form.amount,
                questionnaireTemplate = questionnaireTemplate
        )
    }

    fun toDto(domain: SkillCategoryToPoints): SkillCategoryToPointsDto {
        return SkillCategoryToPointsDto(
                id = domain.id,
                skillCategory = skillCategoryConverter.toShortDto(domain.skillCategory!!),
                amount = domain.amount
        )
    }

}