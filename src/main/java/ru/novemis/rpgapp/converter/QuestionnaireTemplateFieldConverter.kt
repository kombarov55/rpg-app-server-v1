package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateField
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateFieldDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.QuestionnaireTemplateFieldForm

@Component
class QuestionnaireTemplateFieldConverter {

    fun toDomain(form: QuestionnaireTemplateFieldForm, questionnaireTemplate: QuestionnaireTemplate): QuestionnaireTemplateField {
        return QuestionnaireTemplateField(
                name = form.name,
                img = form.img,
                description = form.description,
                type = form.type,
                questionnaireTemplate = questionnaireTemplate
        )
    }

    fun toDto(domain: QuestionnaireTemplateField): QuestionnaireTemplateFieldDto {
        return QuestionnaireTemplateFieldDto(
                id = domain.id,
                name = domain.name,
                description = domain.description,
                img = domain.description,
                type = domain.type
        )
    }

}