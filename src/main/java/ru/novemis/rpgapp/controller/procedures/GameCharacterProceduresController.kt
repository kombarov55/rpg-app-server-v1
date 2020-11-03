package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.questionnaire.SkillToLvl
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.service.BalanceService
import javax.transaction.Transactional

@RestController
@RequestMapping("/gameCharacter")
open class GameCharacterProceduresController(
        private val repository: GameCharacterRepository,
        private val balanceService: BalanceService
) {

    data class UpgradeSkillForm(
            val characterId: String = "",
            val skillId: String = "",
            val chosenPrice: List<PriceForm> = mutableListOf()
    )

    @PostMapping("/upgradeSkill.do")
    @Transactional
    open fun upgradeSkill(
            @RequestBody form: UpgradeSkillForm
    ) {
        val character = repository.findById(form.characterId).get()

        form.chosenPrice.forEach { amount -> balanceService.subtract(character.balance!!.id, amount.name, amount.amount) }

        character.learnedSkills.find { it.skill!!.id == form.skillId }!!.amount += 1
        repository.save(character)
    }

    data class PurchaseSkillForm(
            val characterId: String = "",
            val skillId: String = "",
            val chosenPrice: List<PriceForm> = mutableListOf()
    )

    @PostMapping("/purchaseSkill.do")
    @Transactional
    open fun purchaseSkill(@RequestBody form: PurchaseSkillForm) {
        val character = repository.findById(form.characterId).get()

        form.chosenPrice.forEach { amount -> balanceService.subtract(character.balance!!.id, amount.name, amount.amount) }
        character.learnedSkills += SkillToLvl(skill = Skill(id = form.skillId), character = character)
        repository.save(character)
    }

    data class PurchaseSpellForm(
            val characterId: String = "",
            val spellId: String = "",
            val chosenPrice: List<PriceForm> = mutableListOf()
    )

    @PostMapping("/purchaseSpell.do")
    @Transactional
    open fun purchaseSpell(@RequestBody form: PurchaseSpellForm) {
        val character = repository.findById(form.characterId).get()

        form.chosenPrice.forEach { amount -> balanceService.subtract(character.balance!!.id, amount.name, amount.amount) }
        character.learnedSpells += Spell(id = form.spellId)
        repository.save(character)
    }

}