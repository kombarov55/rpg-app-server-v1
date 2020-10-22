package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.converter.NotificationConverter
import ru.novemis.rpgapp.domain.game.common.TransferDestinationType
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.dto.procedure.dto.NotificationDto
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.organization.OrganizationRepository
import ru.novemis.rpgapp.repository.procedure.NotificationRepository

@Component
class NotificationService(
        private val repository: NotificationRepository,
        private val converter: NotificationConverter,
        private val notificationTemplateService: NotificationTemplateService,
        private val characterRepository: GameCharacterRepository,
        private val organizationRepository: OrganizationRepository
) {

    fun send(notification: Notification) {
        repository.save(notification)
    }

    fun pull(toWhom: Long): List<NotificationDto> {
        return repository.findByRecipientId(toWhom)
                .onEach { repository.delete(it) }
                .map { converter.toDto(it) }
    }

    fun sendTransferNotification(
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

}