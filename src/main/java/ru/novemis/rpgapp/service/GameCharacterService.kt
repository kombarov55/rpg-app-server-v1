package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository

@Component
class GameCharacterService(
        private val repository: GameCharacterRepository,
        private val currencyRepository: CurrencyRepository
) {

    fun createCharacter(questionnaire: Questionnaire) {
        repository.save(generateFromQuestionnaire(questionnaire))
    }

    fun generateFromQuestionnaire(questionnaire: Questionnaire): GameCharacter {
        return GameCharacter().apply {
            val character = this

            balance = currencyRepository.findAllByGameId(questionnaire.game!!.id)
                    .map { currency ->
                        Price(currency = currency, amount = 0)
                    }
            fieldToValueList = questionnaire.fieldToValueList
                    .map { fieldToValue ->
                        fieldToValue.apply { this.character = character }
                    }
            learnedSkills = questionnaire.selectedSkillToLvlList
                    .map { skillToLvl ->
                        skillToLvl.apply { this.character = character }
                    }
            country = questionnaire.country

            learnedSpells = questionnaire.selectedSpells
            this.owner = questionnaire.author
            game = questionnaire.game
            questionnaireTemplate = questionnaire.questionnaireTemplate
        }
    }

}