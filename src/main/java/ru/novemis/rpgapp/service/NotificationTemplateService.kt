package ru.novemis.rpgapp.service

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.procedure.Notification
import ru.novemis.rpgapp.domain.procedure.NotificationSeverity

@Component
class NotificationTemplateService {

    fun questionnaireApproved(recipientId: Long): Notification {
        return Notification(
                text = "Ваша анкета была одобрена!",
                severity = NotificationSeverity.GOOD,
                recipientId = recipientId
        )
    }

    fun questionnaireClarifying(recipientId: Long): Notification {
        return Notification(
                text = "По вашей анкете требуются уточнения. Проверьте свои сообщения ВК.",
                severity = NotificationSeverity.NORMAL,
                recipientId = recipientId
        )
    }

    fun questionnaireArchived(recipientId: Long): Notification {
        return Notification(
                text = "Ваша анкета была отклонена.",
                severity = NotificationSeverity.BAD,
                recipientId = recipientId
        )
    }

    fun transferToPlayer(userId: Long, currency: String, amount: Int, authorName: String): Notification {
        return Notification(
                text = "На ваш счёт поступил перевод в размере $currency: $amount от $authorName.",
                severity = NotificationSeverity.GOOD,
                recipientId = userId
        )
    }

    fun transferToOrganization(userId: Long, organizationName: String, currency: String, amount: Int, authorName: String): Notification {
        return Notification(
                text = "На счёт организации '$organizationName' поступил перевод в размере $currency: $amount от $authorName.",
                severity = NotificationSeverity.GOOD,
                recipientId = userId
        )
    }

    fun itemBought(itemName: String, userId: Long): Notification {
        return Notification(
                text = "На ваш предмет $itemName нашлся покупатель!",
                severity = NotificationSeverity.GOOD,
                recipientId = userId
        )
    }

    fun onItemTransfered(senderName: String, itemName: String, userId: Long): Notification {
        return Notification(
                text = "$senderName передал вам предмет $itemName",
                severity = NotificationSeverity.NORMAL,
                recipientId = userId
        )
    }

    fun onCreditAprooved(userId: Long): Notification {
        return Notification(
                text = "Вам одобрили кредит!",
                severity = NotificationSeverity.NORMAL,
                recipientId = userId
        )
    }

    fun onCreditRejected(userId: Long): Notification {
        return Notification(
                text = "Вам отказали в кредите.",
                severity = NotificationSeverity.NORMAL,
                recipientId = userId
        )
    }
}