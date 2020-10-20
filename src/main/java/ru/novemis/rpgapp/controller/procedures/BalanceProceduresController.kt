package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import javax.transaction.Transactional

@RestController
open class BalanceProceduresController(
        private val gameCharacterRepository: GameCharacterRepository,
        private val organizationRepository: OrganizationRepository
) {


    data class TransferForm(
            val fromId: String = "",
            val toId: String = "",
            val currency: String = "",
            val amount: Int = 0
    )
    @PostMapping("/transfer.do")
    @Transactional
    open fun transfer(
            @RequestBody form: TransferForm
    ) {

    }

}