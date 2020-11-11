package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.character.GameCharacterStatus
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import ru.novemis.rpgapp.dto.game.character.dto.BalanceDto
import ru.novemis.rpgapp.dto.game.character.dto.BalanceType
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import java.util.*
import javax.transaction.Transactional

@Component
open class GameCharacterService(
        private val repository: GameCharacterRepository,
        private val currencyRepository: CurrencyRepository,
        private val userAccountService: UserAccountService
) {

    open fun createCharacter(questionnaire: Questionnaire) {
        val gameCharacter = generateFromQuestionnaire(questionnaire)
        repository.save(gameCharacter)
        userAccountService.setActiveCharacterForGame(gameCharacter.owner!!, gameCharacter.game!!, gameCharacter)
    }

    open fun generateFromQuestionnaire(questionnaire: Questionnaire): GameCharacter {
        return GameCharacter().apply {
            val character = this

            name = questionnaire.name

            balance = Balance().apply {
                amounts = currencyRepository.findAllByGameId(questionnaire.game!!.id).map { currency ->
                    Price(currency = currency, amount = 0, balance = this)
                }
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

    open fun changeStatus(characterId: String, newStatus: GameCharacterStatus) {
        repository.findById(characterId).get()
                .apply {
                    status = newStatus
                    statusChangeDate = Date()
                }.also { repository.save(it) }
    }

    open fun getBalances(characterId: String): List<BalanceDto> {
        val character = repository.findById(characterId).get()
        return character.managingOrganizations.map { organization ->
            BalanceDto(id = organization.balance!!.id, name = organization.name, type = BalanceType.ORGANIZATION)
        } + BalanceDto(id = character.balance!!.id, name = character.name, type = BalanceType.CHARACTER)
    }

}