package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.QuestionnaireTemplateForm

@Component
class QuestionnaireTemplateConverter(
        private val questionnaireTemplateFieldConverter: QuestionnaireTemplateFieldConverter,
        private val skillCategoryToPointsConverter: SkillCategoryToPointsConverter
) {

    fun toDomain(form: QuestionnaireTemplateForm, game: Game? = null): QuestionnaireTemplate {
        return QuestionnaireTemplate(
                name = form.name,
                img = form.img,
                description = form.description,
                game = game
        )
    }

    fun toDto(domain: QuestionnaireTemplate): QuestionnaireTemplateDto {
        return QuestionnaireTemplateDto(
                id = domain.id,
                name = domain.name,
                img = domain.img,
                description = domain.description,
                fields = domain.fields.map { questionnaireTemplateFieldConverter.toDto(it) },
                skillCategoryToPoints = domain.skillCategoryToPoints.map { skillCategoryToPointsConverter.toDto(it) }
        )
    }

    fun toShortDto(domain: QuestionnaireTemplate): QuestionnaireTemplateShortDto {
        return QuestionnaireTemplateShortDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                img = domain.img
        )
    }

}