package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.UpgradeCost
import ru.novemis.rpgapp.domain.game.skill.UpgradeCostOption
import ru.novemis.rpgapp.domain.game.skill.UpgradeCostOptionEntry
import ru.novemis.rpgapp.domain.game.skill.UpgradeOption
import ru.novemis.rpgapp.dto.questionnaire.SkillForm
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository

@Component
class SkillConverter(
        private val skillTypeRepository: SkillTypeRepository,
        private val currencyRepository: CurrencyRepository
) {

    fun toDomain(gameId: String, skillForm: SkillForm): Skill {
        return Skill().apply {

            val thatSkill = this

            name = skillForm.name
            description = skillForm.description
            skillType = skillTypeRepository.findByGameIdAndName(gameId, skillForm.type)

            currenciesForUpgrade = skillForm.currenciesForUpgrade.map { currencyName ->
                currencyRepository.findByGameIdAndName(gameId, currencyName)
                        ?: throw IllegalArgumentException()
            }

            upgradeOptions = skillForm.upgradeOptions.map { upgradeOptionForm ->
                UpgradeOption(
                        skill = thatSkill,

                        currencies = upgradeOptionForm.currencies.map { currencyName ->
                            currencyRepository.findByGameIdAndName(gameId, currencyName)
                                    ?: throw IllegalArgumentException ()
                        }
                )
            }

            upgradeCosts = skillForm.upgradeCosts.map { upgradeCostForm ->
                UpgradeCost().apply {
                    val thatUpgradeCost = this

                    skill = thatSkill
                    lvlNum = upgradeCostForm.lvlNum

                    upgradeCostOptions = upgradeCostForm.options.map { skillUpgradeCostOption ->
                        UpgradeCostOption().apply {
                            val thatUpgradeCostOption = this

                            upgradeCost = thatUpgradeCost

                            upgradeCostOptionEntries = skillUpgradeCostOption.costs.map { cost ->
                                UpgradeCostOptionEntry(
                                        upgradeCostOption = thatUpgradeCostOption,
                                        currency = currencyRepository.findByGameIdAndName(gameId, cost.currencyName),
                                        amount = cost.amount
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}