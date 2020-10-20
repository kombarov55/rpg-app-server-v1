package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.service.BalanceService
import ru.novemis.rpgapp.service.NotificationService
import ru.novemis.rpgapp.service.NotificationTemplateService
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
@RequestMapping("/balance")
open class BalanceProceduresController(
        private val service: BalanceService,
        private val notificationService: NotificationService,
        private val notificationTemplateService: NotificationTemplateService,
        private val characterRepository: GameCharacterRepository,
        private val organizationRepository: OrganizationRepository,
        private val jwtUtil: JWTUtil
) {


    data class TransferForm(
            val from: String = "",
            val to: String = "",
            val currency: String = "",
            val amount: Int = 0,
            val originId: String = "",
            val originType: TransferDestinationType = TransferDestinationType.CHARACTER,
            val destinationId: String = "",
            val destinationType: TransferDestinationType = TransferDestinationType.CHARACTER
    )

    @PostMapping("/transfer.do")
    @Transactional
    open fun transfer(
            @RequestBody form: TransferForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        service.transfer(form.from, form.to, form.currency, form.amount)
        if (form.destinationType == TransferDestinationType.CHARACTER) {
            notificationService.send(notificationTemplateService.transferToPlayer(
                    userId = characterRepository.findById(form.destinationId).get().owner!!.userId,
                    currency = form.currency,
                    amount = form.amount,
                    authorName = when (form.originType) {
                        TransferDestinationType.CHARACTER -> characterRepository.findById(form.originId).get().name
                        TransferDestinationType.ORGANIZATION -> organizationRepository.findById(form.originId).get().name
                    }
            ))
        } else {
            val organization = organizationRepository.findById(form.destinationId).get()

            organization.organizationHeads.forEach { character ->
                notificationService.send(notificationTemplateService.transferToOrganization(
                        userId = character.owner!!.userId,
                        organizationName = organization.name,
                        currency = form.currency,
                        amount = form.amount,
                        authorName = when (form.originType) {
                            TransferDestinationType.CHARACTER -> characterRepository.findById(form.originId).get().name
                            TransferDestinationType.ORGANIZATION -> organizationRepository.findById(form.originId).get().name
                        }
                ))
            }
        }
    }

}