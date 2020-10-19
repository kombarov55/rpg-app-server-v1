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

}