package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateItem
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateItemListValue
import ru.novemis.rpgapp.domain.game.questionnaire_template.enum.QuestionnaireItemType
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateItemForm

@Component
class QuestionnaireTemplateItemConverter {

    fun toDomain(thatQuestionnaire: QuestionnaireTemplate, questionnaireItem: QuestionnaireTemplateItemForm): QuestionnaireTemplateItem {
        return QuestionnaireTemplateItem().apply {
            val thatQuestionnaireItem = this

            name = questionnaireItem.name
            type = QuestionnaireItemType.valueOf(questionnaireItem.type.toUpperCase())
            questionnaireTemplate = thatQuestionnaire
            listValues = questionnaireItem.listValues?.map { listValue ->

                QuestionnaireTemplateItemListValue(
                        questionnaireTemplateItem = thatQuestionnaireItem,
                        value = listValue
                )
            } ?: mutableListOf()
        }
    }

    fun toDto(questionnaireItem: QuestionnaireTemplateItem): QuestionnaireTemplateItemForm {
        return QuestionnaireTemplateItemForm(
                id = questionnaireItem.id,
                name = questionnaireItem.name,
                type = questionnaireItem.type.name,
                listValues = questionnaireItem.listValues.map { it.value }
        )
    }

}