package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.SkillPointsDistribution
import ru.novemis.rpgapp.dto.game.questionnaire_template.SkillPointsDistributionForm
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository

@Component
class SkillPointDistributionConverter(
        private val skillTypeRepository: SkillTypeRepository
) {

    fun toDomain(questionnaireTemplate: QuestionnaireTemplate, skillPointsDistributionForm: SkillPointsDistributionForm): SkillPointsDistribution {
        return SkillPointsDistribution(
                skillType = skillTypeRepository.findByGameIdAndName(questionnaireTemplate.game!!.id, skillPointsDistributionForm.skillType),
                maxValue = skillPointsDistributionForm.maxValue,
                questionnaireTemplate = questionnaireTemplate
        )
    }

    fun toDto(skillPointsDistribution: SkillPointsDistribution): SkillPointsDistributionForm {
        return SkillPointsDistributionForm(
                id = skillPointsDistribution.id,
                skillType = skillPointsDistribution.skillType!!.name,
                maxValue = skillPointsDistribution.maxValue
        )
    }

}