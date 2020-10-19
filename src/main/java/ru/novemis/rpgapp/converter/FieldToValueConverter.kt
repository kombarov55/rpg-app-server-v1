package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire.FieldToValue
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.dto.game.questionnaire.form.FieldToValueForm
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateFieldRepository

@Component
class FieldToValueConverter(
        private val questionnaireTemplateFieldRepository: QuestionnaireTemplateFieldRepository
) {

    fun toDomain(form: FieldToValueForm, questionnaire: Questionnaire? = null): FieldToValue {
        return FieldToValue(
                field = questionnaireTemplateFieldRepository.findById(form.field!!.id!!).get(),
                value = form.value,
                questionnaire = questionnaire
        )
    }

}