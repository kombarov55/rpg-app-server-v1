package ru.novemis.rpgapp.repository.game.questionnaire_template

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.questionnaire_template.SkillPointsDistribution

interface SkillPointsDistributionRepository : CrudRepository<SkillPointsDistribution, String> {
}