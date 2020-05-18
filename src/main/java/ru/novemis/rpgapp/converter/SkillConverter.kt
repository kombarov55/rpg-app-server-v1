package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.*
import ru.novemis.rpgapp.dto.game.skill.*
import ru.novemis.rpgapp.repository.game.CurrencyRepository
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.skill.SkillTypeRepository

@Component
class SkillConverter(
        private val skillTypeRepository: SkillTypeRepository,
        private val currencyRepository: CurrencyRepository,
        private val gameRepository: GameRepository
) {

    fun toDomain(skillForm: SkillForm): Skill {
        return Skill().apply {

            val thatSkill = this

            game = gameRepository.findById(skillForm.gameId).orElseThrow { IllegalArgumentException() }

            name = skillForm.name
            description = skillForm.description
            skillType = skillTypeRepository.findByGameIdAndName(skillForm.gameId, skillForm.type)

            currenciesForUpgrade = skillForm.currenciesForUpgrade.map { currencyName ->
                currencyRepository.findByGameIdAndName(skillForm.gameId, currencyName)
                        ?: throw IllegalArgumentException()
            }

            upgradeOptions = skillForm.upgradeOptions.map { upgradeOptionForm ->
                UpgradeOption(
                        skill = thatSkill,

                        currencies = upgradeOptionForm.currencies.map { currencyName ->
                            currencyRepository.findByGameIdAndName(skillForm.gameId, currencyName)
                                    ?: throw IllegalArgumentException()
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
                                        currency = currencyRepository.findByGameIdAndName(skillForm.gameId, cost.currencyName),
                                        amount = cost.amount
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun toDto(skill: Skill): SkillDto {
        return SkillDto(
                id = skill.id,
                name = skill.name,
                type = skill.skillType!!.name,
                description = skill.description,
                imgSrc = skill.imgSrc,
                upgradeCosts = skill.upgradeCosts.map { upgradeCost ->
                    UpgradeCostDto(
                            lvlNum = upgradeCost.lvlNum,
                            options = upgradeCost.upgradeCostOptions.map { upgradeCostOption ->
                                UpgradeCostOptionDto(
                                        costs = upgradeCostOption.upgradeCostOptionEntries.map { upgradeCostOptionEntry ->
                                            UpgradeCostOptionEntryDto(
                                                    currencyName = upgradeCostOptionEntry.currency!!.name,
                                                    amount = upgradeCostOptionEntry.amount
                                            )
                                        }
                                )
                            }
                    )
                }
        )
    }

    fun toForm(skill: Skill): SkillForm {
        return SkillForm(
                id = skill.id,
                name = skill.name,
                type = skill.skillType!!.name,
                description = skill.description,
                imgSrc = skill.imgSrc,
                currenciesForUpgrade = skill.currenciesForUpgrade.map { currency -> currency.name },
                upgradeOptions = skill.upgradeOptions.map { upgradeOption ->
                    SkillUpgradeOptionForm(
                            currencies = upgradeOption.currencies.map { currency -> currency.name }
                    )
                },
                upgradeCosts = skill.upgradeCosts.map { upgradeCost ->
                    UpgradeCostDto(
                            lvlNum = upgradeCost.lvlNum,
                            options = upgradeCost.upgradeCostOptions.map { upgradeCostOption ->
                                UpgradeCostOptionDto(
                                        costs = upgradeCostOption.upgradeCostOptionEntries.map { upgradeCostOptionEntry ->
                                            UpgradeCostOptionEntryDto(
                                                    currencyName = upgradeCostOptionEntry.currency!!.name,
                                                    amount = upgradeCostOptionEntry.amount
                                            )
                                        }
                                )
                            }
                    )
                }
        )
    }

}