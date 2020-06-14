package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateItemRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.SkillPointsDistributionRepository

@Component
class QuestionnaireTemplateConverter(
        private val gameRepository: GameRepository,
        private val questionnaireTemplateItemConverter: QuestionnaireTemplateItemConverter,
        private val questionnaireTemplateItemRepository: QuestionnaireTemplateItemRepository,
        private val skillPointDistributionConverter: SkillPointDistributionConverter,
        private val skillPointsDistributionRepository: SkillPointsDistributionRepository
) {

    fun toDomain(form: QuestionnaireTemplateForm): QuestionnaireTemplate {
        return QuestionnaireTemplate().apply {
            val thatQuestionnaire = this

            name = form.name
            description = form.description

            game = gameRepository.findById(form.gameId).orElseThrow { IllegalArgumentException() }

            templateItems = form.questionnaireTemplateItems.map { questionnaireItem ->
                questionnaireItem.id
                        ?.let { questionnaireTemplateItemRepository.findById(it).orElseThrow { java.lang.IllegalArgumentException() } }
                        ?: questionnaireTemplateItemConverter.toDomain(thatQuestionnaire, questionnaireItem)
            }

            skillPointsDistributions = form.skillPointsDistribution.map { skillFormDistributionForm ->
                skillFormDistributionForm.id
                        ?.let {
                            skillPointsDistributionRepository
                                    .findById(it).orElseThrow { IllegalArgumentException() }
                                    .apply { maxValue = skillFormDistributionForm.maxValue }
                                    .also { skillPointsDistributionRepository.save(it) }
                        } ?: skillPointDistributionConverter.toDomain(thatQuestionnaire, skillFormDistributionForm)
            }
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
            questionnaireTemplateItems = questionnaireTemplate.templateItems.map { questionnaireTemplateItemConverter.toDto(it) }
            skillPointsDistribution = questionnaireTemplate.skillPointsDistributions.map { skillPointDistributionConverter.toDto(it) }
            skills = emptyList()
        }
    }
}