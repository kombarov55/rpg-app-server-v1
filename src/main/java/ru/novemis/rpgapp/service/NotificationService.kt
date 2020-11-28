package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NotificationConverter
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.CreditRequestRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.game.shop.ItemForSaleRepository
import ru.novemis.rpgapp.repository.game.shop.ItemRepository
import ru.novemis.rpgapp.repository.procedure.NotificationRepository
import javax.transaction.Transactional

@Component
open class NotificationService(
        private val repository: NotificationRepository,
        private val converter: NotificationConverter,
        private val notificationTemplateService: NotificationTemplateService,
        private val characterRepository: GameCharacterRepository,
        private val organizationRepository: OrganizationRepository,
        private val itemForSaleRepository: ItemForSaleRepository,
        private val itemRepository: ItemRepository,
        private val creditRequestRepository: CreditRequestRepository
) {

    open fun send(notification: Notification) {
        repository.save(notification)
    }

    open fun pull(toWhom: Long): List<NotificationDto> {
        return repository.findByRecipientId(toWhom)
                .onEach { repository.delete(it) }
                .map { converter.toDto(it) }
    }

    open fun sendTransferNotification(
            amount: Int,
            currency: String,
            destinationType: TransferDestinationType,
            destinationId: String,
            originType: TransferDestinationType,
            originId: String? = null
    ) {
        if (destinationType == TransferDestinationType.CHARACTER) {
            send(notificationTemplateService.transferToPlayer(
                    userId = characterRepository.findById(destinationId).get().owner!!.userId,
                    currency = currency,
                    amount = amount,
                    authorName = when (originType) {
                        TransferDestinationType.CHARACTER -> characterRepository.findById(originId!!).get().name
                        TransferDestinationType.ORGANIZATION -> organizationRepository.findById(originId!!).get().name
                        TransferDestinationType.ADMIN -> "Администратора"
                    }
            ))
        } else {
            val organization = organizationRepository.findById(destinationId).get()

            organization.heads.forEach { character ->
                send(notificationTemplateService.transferToOrganization(
                        userId = character.owner!!.userId,
                        organizationName = organization.name,
                        currency = currency,
                        amount = amount,
                        authorName = when (originType) {
                            TransferDestinationType.CHARACTER -> characterRepository.findById(originId!!).get().name
                            TransferDestinationType.ORGANIZATION -> organizationRepository.findById(originId!!).get().name
                            TransferDestinationType.ADMIN -> "Администратора"
                        }
                ))
            }
        }
    }

    open fun sendItemBoughtNotification(itemForSaleId: String) {
        val itemForSale = itemForSaleRepository.findById(itemForSaleId).get()
        val userId = itemForSale.owner!!.owner!!.userId

        send(notificationTemplateService.itemBought(itemForSale.item!!.itemTemplate!!.name, userId))
    }

    @Transactional
    open fun onItemTransfered(itemId: String, fromId: String, fromType: TransferDestinationType, toId: String, toType: TransferDestinationType) {
        val itemName = itemRepository.findById(itemId).get().itemTemplate!!.name
        val senderName = when(fromType) {
            TransferDestinationType.CHARACTER -> characterRepository.findById(fromId).get().name
            TransferDestinationType.ORGANIZATION -> organizationRepository.findById(fromId).get().name
            TransferDestinationType.ADMIN -> "Администратор"
        }
        val recipientIds = when(toType) {
            TransferDestinationType.CHARACTER -> listOf(characterRepository.findById(toId).get().owner!!.userId)
            TransferDestinationType.ORGANIZATION -> organizationRepository.findById(toId).get().heads.map { character -> character.owner!!.userId }
            TransferDestinationType.ADMIN -> throw RuntimeException("Impossible")
        }

        recipientIds.forEach { recipientId ->
            send(notificationTemplateService.onItemTransfered(senderName, itemName, recipientId))
        }
    }

    open fun onCreditAprooved(creditRequestId: String) {
        send(notificationTemplateService.onCreditAprooved(creditRequestRepository.findById(creditRequestId).get().requester!!.owner!!.userId))
    }

    open fun onCreditRejected(creditRequestId: String) {
        send(notificationTemplateService.onCreditRejected(creditRequestRepository.findById(creditRequestId).get().requester!!.owner!!.userId))
    }

    open fun onCreditPaid(credit: Credit) {
        send(notificationTemplateService.onCreditPaid(credit.owner!!.owner!!.userId))
    }

    open fun onCreditOverdue(credit: Credit) {
        send(notificationTemplateService.onCreditOverdue(credit.debtAmount, credit.currency!!.name, credit.owner!!.owner!!.userId))
    }

}