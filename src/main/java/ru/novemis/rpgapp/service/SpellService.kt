package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.skill.SchoolLvl
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import javax.transaction.Transactional

@Component
open class SpellService(
        private val skillCategoryRepository: SkillCategoryRepository
) {

    @Transactional
    open fun findAvailableSpells(gameId: String, character: GameCharacter): List<Spell> {
        val skillCategories = skillCategoryRepository.findAllComplexByGameId(gameId)

        val schoolLvlToAmountOfLearnedSpells: List<Pair<SchoolLvl, Int>> = character.learnedSpells.groupBy { spell -> spell.schoolLvl!! }
                .map { (schoolLvl, spells) -> schoolLvl to spells.size }

        val allSchoolLvls = skillCategories.flatMap { it.spellSchools }.flatMap { it.schoolLvls }

        return allSchoolLvls.filter { schoolLvl ->
            val lvl = schoolLvl.lvl

            if (lvl == 1) {
                true
            } else {
                val minSpellCountToUnlockLvl = schoolLvl.spellSchool!!.minSpellCountToUpgrade
                val prevLvl = schoolLvl.spellSchool!!.getLvl(lvl - 1)!!
                val learnedAmount = schoolLvlToAmountOfLearnedSpells.find { (schoolLvl, _) -> schoolLvl.id == prevLvl.id }?.second
                        ?: 0

                learnedAmount >= minSpellCountToUnlockLvl
            }
        }.flatMap { it.spells }.filter { spell ->
            character.learnedSpells.none { learnedSpell -> learnedSpell.id == spell.id }
        }
    }

}