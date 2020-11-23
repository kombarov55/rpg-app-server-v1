package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType.*
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.service.BalanceService
import ru.novemis.rpgapp.service.NotificationService
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@RestController
@RequestMapping("/balance")
open class BalanceProceduresController(
        private val service: BalanceService,
        private val notificationService: NotificationService,
        private val characterRepository: GameCharacterRepository,
        private val organizationRepository: OrganizationRepository
) {


    data class TransferForm(
            /**
             * balanceID отправителя
             */
            val from: String = "",
            /**
             * balanceID получателя
             */
            val to: String = "",
            /**
             * Название валюты
             */
            val currency: String = "",
            /**
             * Количество
             */
            val amount: Int = 0,
            /**
             * id отправителя
             */
            val originId: String = "",
            /**
             * Тип отправителя
             */
            val originType: TransferDestinationType = CHARACTER,
            /**
             * id получателя
             */
            val destinationId: String = "",
            /**
             * Тип получателя
             */
            val destinationType: TransferDestinationType = CHARACTER
    )

    @PostMapping("/transfer.do")
    @Transactional
    open fun transfer(
            @RequestBody form: TransferForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        service.transfer(getGameId(form.destinationId, form.destinationType), form.from, form.to, form.currency, form.amount)
        notificationService.sendTransferNotification(form.amount, form.currency, form.destinationType, form.destinationId, form.originType, form.originId)
    }

    data class AdminTransferForm(
            val to: String = "",
            val currency: String = "",
            val amount: Int = 0,
            val destinationId: String = "",
            val destinationType: TransferDestinationType = CHARACTER
    )

    @PostMapping("/adminTransfer.do")
    @Transactional
    open fun adminTransfer(
            @RequestBody form: AdminTransferForm
    ) {
        service.add(getGameId(form.destinationId, form.destinationType), form.to, form.currency, form.amount)
        notificationService.sendTransferNotification(form.amount, form.currency, form.destinationType, form.destinationId, ADMIN)
    }

    data class ConversionRq(
            val currency1Id: String = "",
            val currency2Id: String = "",
            val amount: Int = 0,
            val characterId: String = ""
    )

    @PostMapping("/convert.do")
    @Transactional
    open fun convert(@RequestBody rq: ConversionRq) {
        service.convert(rq.currency1Id, rq.currency2Id, rq.amount, rq.characterId)
    }

    private fun getGameId(destination: String, destinationType: TransferDestinationType): String {
        return when (destinationType) {
            CHARACTER -> characterRepository.findById(destination).get().game!!.id
            ORGANIZATION -> organizationRepository.findById(destination).get().game!!.id
            else -> throw IllegalArgumentException()
        }
    }
}