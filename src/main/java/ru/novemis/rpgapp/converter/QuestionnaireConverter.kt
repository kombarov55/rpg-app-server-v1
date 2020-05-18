package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.domain.game.questionnaire.QuestionnaireItem
import ru.novemis.rpgapp.domain.game.questionnaire.SkillPointsDistribution
import ru.novemis.rpgapp.domain.game.questionnaire.enum.QuestionnaireItemType
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireShortDto
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository

@Component
class QuestionnaireConverter(
        private val gameRepository: GameRepository,
        private val skillTypeRepository: SkillTypeRepository,
        private val currencyRepository: CurrencyRepository,
        private val skillConverter: SkillConverter
) {

    fun toDomain(form: QuestionnaireForm): Questionnaire {
        return Questionnaire().apply {
            name = form.name
            description = form.description

            items = form.questionnaireItems.map {
                QuestionnaireItem(
                        name = it.name,
                        type = QuestionnaireItemType.valueOf(it.type.toUpperCase()),
                        questionnaire = this
                )
            }

            skillPointsDistributions = form.skillPointsDistribution.map {
                SkillPointsDistribution(
                        skillType = skillTypeRepository.findByGameIdAndName(form.gameId, it.skillType),
                        maxValue = it.maxValue,
                        questionnaire = this
                )
            }

            game = gameRepository.findById(form.gameId).orElseThrow { IllegalArgumentException() }
        }
    }

    fun toShortDto(questionnaire: Questionnaire): QuestionnaireShortDto {
        return QuestionnaireShortDto(
                id = questionnaire.id,
                name = questionnaire.name,
                description = questionnaire.description,
                imgSrc = questionnaire.imgSrc
        )
    }
}