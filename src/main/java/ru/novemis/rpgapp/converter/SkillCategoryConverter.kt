package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.*
import ru.novemis.rpgapp.dto.game.skill.dto.*
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class SkillCategoryConverter(
        private val priceCombinationConverter: PriceCombinationConverter,
        private val gameRepository: GameRepository
) {

    fun toDomain(skillCategoryForm: SkillCategoryForm, gameId: String): SkillCategory {
        return SkillCategory().apply {
            val skillCategory = this

            name = skillCategoryForm.name
            description = skillCategoryForm.description
            img = skillCategoryForm.img
            complex = skillCategoryForm.complex

            skills = skillCategoryForm.skills?.map {
                Skill().apply {
                    val skill = this

                    name = it.name
                    description = it.description
                    img = it.img
                    prices = it.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
                    upgradable = it.upgradable
                    this.skillCategory = skillCategory

                    upgrades = it.skillUpgrades.map { skillUpgradeForm ->
                        SkillUpgrade(
                                lvlNum = skillUpgradeForm.lvlNum,
                                description = skillUpgradeForm.description,
                                prices = skillUpgradeForm.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) },
                                skill = skill
                        )
                    }
                }
            } ?: emptyList()

            spellSchools = skillCategoryForm.spellSchools?.map { spellSchoolForm ->
                SpellSchool().apply {
                    val spellSchool = this

                    name = spellSchoolForm.name
                    description = spellSchoolForm.description
                    img = spellSchoolForm.img
                    minSpellCountToUpgrade = spellSchoolForm.minSpellCountToUpgrade
                    purchasePriceCombinations = spellSchoolForm.purchasePriceCombinations.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
                    schoolLvls = spellSchoolForm.schoolLvls.map { schoolLvlForm ->
                        SchoolLvl().apply {
                            val schoolLvl = this

                            lvl = schoolLvlForm.lvl
                            this.spellSchool = spellSchool
                            upgradePriceCombinations = schoolLvlForm.schoolLvlUpgradePriceCombinations.map { listOfPrices ->
                                priceCombinationConverter.toDomain(listOfPrices, gameId)
                            }

                            spells = schoolLvlForm.spells.map { spellForm ->
                                Spell(
                                        name = spellForm.name,
                                        description = spellForm.description,
                                        img = spellForm.img,
                                        schoolLvl = schoolLvl
                                )
                            }
                        }
                    }
                }
            } ?: emptyList()

            game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        }
    }

    fun toDto(skillCategory: SkillCategory): SkillCategoryDto {
        return SkillCategoryDto(
                img = skillCategory.img,
                name = skillCategory.name,
                description = skillCategory.description,
                complex = skillCategory.complex,
                skills = skillCategory.skills.map {
                    SkillDto(
                            img = it.img,
                            name = it.name,
                            description = it.description,
                            prices = it.prices.map { priceCombinationConverter.toString(it) },
                            upgrades = it.upgrades.map {
                                SkillUpgradeDto(
                                        lvlNum = it.lvlNum,
                                        description = it.description,
                                        prices = it.prices.map { priceCombinationConverter.toString(it) }
                                )
                            }
                    )
                },
                spellSchools = skillCategory.spellSchools.map {
                    SpellSchoolDto(
                            img = it.img,
                            name = it.name,
                            description = it.description,
                            minSpellCountToUpgrade = it.minSpellCountToUpgrade,
                            schoolLvls = it.schoolLvls.map {
                                SchoolLvlDto(
                                        lvl = it.lvl,
                                        upgradePriceCombinations = it.upgradePriceCombinations.map { priceCombinationConverter.toString(it) },
                                        spells = it.spells.map {
                                            SpellDto(
                                                    img = it.img,
                                                    name = it.name,
                                                    description = it.description
                                            )
                                        }
                                )
                            }
                    )
                }
        )
    }

}