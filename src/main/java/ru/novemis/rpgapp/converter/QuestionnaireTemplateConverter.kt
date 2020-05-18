package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateItem
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplateItemListValue
import ru.novemis.rpgapp.domain.game.questionnaire_template.SkillPointsDistribution
import ru.novemis.rpgapp.domain.game.questionnaire_template.enum.QuestionnaireItemType
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateItemForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.SkillPointsDistributionForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository

@Component
class QuestionnaireTemplateConverter(
        private val gameRepository: GameRepository,
        private val skillTypeRepository: SkillTypeRepository,
        private val currencyRepository: CurrencyRepository,
        private val skillConverter: SkillConverter
) {

    fun toDomain(form: QuestionnaireTemplateForm): QuestionnaireTemplate {
        return QuestionnaireTemplate().apply {
            val thatQuestionnaire = this

            name = form.name
            description = form.description

            templateItems = form.questionnaireTemplateItems.map { questionnaireItem ->
                QuestionnaireTemplateItem().apply {
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

            skillPointsDistributions = form.skillPointsDistribution.map {
                SkillPointsDistribution(
                        skillType = skillTypeRepository.findByGameIdAndName(form.gameId, it.skillType),
                        maxValue = it.maxValue,
                        questionnaireTemplate = this
                )
            }

            game = gameRepository.findById(form.gameId).orElseThrow { IllegalArgumentException() }
        }
    }

    fun toShortDto(questionnaireTemplate: QuestionnaireTemplate): QuestionnaireTemplateShortDto {
        return QuestionnaireTemplateShortDto(
                id = questionnaireTemplate.id,
                name = questionnaireTemplate.name,
                description = questionnaireTemplate.description,
                imgSrc = questionnaireTemplate.imgSrc
        )
    }

    fun toDto(questionnaireTemplate: QuestionnaireTemplate): QuestionnaireTemplateForm {
        return QuestionnaireTemplateForm().apply {
            id = questionnaireTemplate.id
            gameId = questionnaireTemplate.game!!.id
            name = questionnaireTemplate.name
            description = questionnaireTemplate.description
            questionnaireTemplateItems = questionnaireTemplate.templateItems.map {
                QuestionnaireTemplateItemForm(
                        name = it.name,
                        type = it.type.name,
                        listValues = it.listValues.map { it.value }
                )
            }
            skillPointsDistribution = questionnaireTemplate.skillPointsDistributions.map {
                SkillPointsDistributionForm(
                        it.skillType!!.name,
                        it.maxValue
                )
            }
        }
    }
}