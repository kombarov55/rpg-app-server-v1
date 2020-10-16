package ru.novemis.rpgapp.repository.game.questionnaire_template

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateField

interface QuestionnaireTemplateFieldRepository : CrudRepository<QuestionnaireTemplateField, String> {
}