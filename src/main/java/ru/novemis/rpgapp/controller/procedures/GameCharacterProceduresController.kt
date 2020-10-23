package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

}