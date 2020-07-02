package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.*
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.GameRepository

@Component
class SkillCategoryConverter(
        private val priceCombinationConverter: PriceCombinationConverter,
        private val gameRepository: GameRepository
) {

    fun toDomain(skillCategoryForm: SkillCategoryForm, gameId: String): SkillCategory {
        return SkillCategory().apply {
            name = skillCategoryForm.name
            description = skillCategoryForm.description
            img = skillCategoryForm.img
            complex = skillCategoryForm.complex

            skills = skillCategoryForm.skills?.map {
                Skill().apply {
                    name = it.name
                    description = it.description
                    img = it.img
                    prices = it.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
                    upgradable = it.upgradable
                    upgrades = it.skillUpgrades.map { skillUpgradeForm ->
                        SkillUpgrade(
                                lvlNum = skillUpgradeForm.lvlNum,
                                description = skillUpgradeForm.description,
                                prices = skillUpgradeForm.prices.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) }
                        )
                    }
                }
            } ?: emptyList()

            spellSchools = skillCategoryForm.spellSchools?.map { spellSchoolForm ->
                SpellSchool(
                        name = spellSchoolForm.name,
                        description = spellSchoolForm.description,
                        img = spellSchoolForm.img,
                        minSpellCountToUpgrade = spellSchoolForm.minSpellCountToUpgrade,
                        purchasePriceCombinations = spellSchoolForm.purchasePriceCombinations.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) },
                        schoolLvls = spellSchoolForm.schoolLvls.map { schoolLvlForm ->
                            SchoolLvl(
                                    lvl = schoolLvlForm.lvl,
                                    upgradePriceCombinations = schoolLvlForm.schoolLvlUpgradePriceCombinations.map { listOfPrices -> priceCombinationConverter.toDomain(listOfPrices, gameId) },
                                    spells = schoolLvlForm.spells.map { spellForm ->
                                        Spell(
                                                name = spellForm.name,
                                                description = spellForm.description,
                                                img = spellForm.img
                                        )
                                    }
                            )
                        }
                )
            } ?: emptyList()

            game = gameRepository.findById(gameId).orElseThrow { IllegalArgumentException("gameId is invalid") }
        }
    }

}