package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.SkillPointsDistribution
import ru.novemis.rpgapp.dto.game.questionnaire_template.SkillPointsDistributionForm

@Component
class SkillPointDistributionConverter(
) {

    fun toDomain(questionnaireTemplate: QuestionnaireTemplate, skillPointsDistributionForm: SkillPointsDistributionForm): SkillPointsDistribution {
        return SkillPointsDistribution(
                maxValue = skillPointsDistributionForm.maxValue,
                questionnaireTemplate = questionnaireTemplate
        )
    }

    fun toDto(skillPointsDistribution: SkillPointsDistribution): SkillPointsDistributionForm {
        return SkillPointsDistributionForm(
                id = skillPointsDistribution.id,
                maxValue = skillPointsDistribution.maxValue
        )
    }

}