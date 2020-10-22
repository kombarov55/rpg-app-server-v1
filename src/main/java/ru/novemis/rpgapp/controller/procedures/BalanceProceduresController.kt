package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.service.BalanceService
import ru.novemis.rpgapp.service.NotificationService
import javax.transaction.Transactional

@RestController
@RequestMapping("/balance")
open class BalanceProceduresController(
        private val service: BalanceService,
        private val notificationService: NotificationService
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
        notificationService.sendTransferNotification(form.amount, form.currency, form.destinationType, form.destinationId, form.originType, form.originId)
    }

    data class AdminTransferForm(
            val to: String = "",
            val currency: String = "",
            val amount: Int = 0,
            val destinationId: String = "",
            val destinationType: TransferDestinationType = TransferDestinationType.CHARACTER
    )

    @PostMapping("/adminTransfer.do")
    @Transactional
    open fun adminTransfer(
            @RequestBody form: AdminTransferForm
    ) {
        service.add(form.to, form.currency, form.amount)
        notificationService.sendTransferNotification(form.amount, form.currency, form.destinationType, form.destinationId, TransferDestinationType.ADMIN)
    }
}