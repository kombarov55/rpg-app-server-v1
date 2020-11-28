package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.questionnaire.SkillToLvl
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.domain.game.skill.Skill
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.dto.game.common.form.PriceForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellRepository
import ru.novemis.rpgapp.service.BalanceService
import javax.transaction.Transactional

@RestController
@RequestMapping("/gameCharacter")
open class GameCharacterProceduresController(
        private val repository: GameCharacterRepository,
        private val balanceService: BalanceService,
        private val spellRepository: SpellRepository,
        private val itemRepository: ItemRepository
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

        form.chosenPrice.forEach { amount -> balanceService.subtract(character.game!!.id, character.balance!!.id, amount.name, amount.amount) }

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

        form.chosenPrice.forEach { amount -> balanceService.subtract(character.game!!.id, character.balance!!.id, amount.name, amount.amount) }
        character.learnedSkills += SkillToLvl(skill = Skill(id = form.skillId), character = character)
        repository.save(character)
    }

    data class PurchaseSpellRq(
            val characterId: String = "",
            val spellId: String = "",
            val chosenPrice: List<PriceForm> = mutableListOf()
    )

    data class PurchaseSpellRs(
            val isNextLvlUnlocked: Boolean
    )

    @PostMapping("/purchaseSpell.do")
    @Transactional
    open fun purchaseSpell(@RequestBody rq: PurchaseSpellRq): PurchaseSpellRs {
        val character = repository.findById(rq.characterId).get()

        rq.chosenPrice.forEach { amount -> balanceService.subtract(character.game!!.id, character.balance!!.id, amount.name, amount.amount) }
        character.learnedSpells += Spell(id = rq.spellId)
        repository.save(character)

        val spell = spellRepository.findById(rq.spellId).get()
        val amountOfLearnedSpells = character.learnedSpells.filter { learnedSpell -> learnedSpell.schoolLvl == spell.schoolLvl }.size
        val minSpellCountToUpgrade = spell.schoolLvl!!.spellSchool!!.minSpellCountToUpgrade


        return PurchaseSpellRs(
                isNextLvlUnlocked = amountOfLearnedSpells == minSpellCountToUpgrade
        )
    }

    data class DisposeItemRq(
            val characterId: String = "",
            val itemId: String = ""
    )

    @PostMapping("/disposeItem.do")
    @Transactional
    open fun disposeItem(@RequestBody rq: DisposeItemRq) {
        repository.findById(rq.characterId).get()
                .removeItem(rq.itemId)
                .let { repository.save(it) }

        itemRepository.deleteById(rq.itemId)
    }
}