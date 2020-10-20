package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.questionnaire.QuestionnaireStatus
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import ru.novemis.rpgapp.service.GameCharacterService
import ru.novemis.rpgapp.service.NotificationService
import ru.novemis.rpgapp.service.NotificationTemplateService
import ru.novemis.rpgapp.service.QuestionnaireService
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
@RequestMapping("/questionnaire")
open class QuestionnaireProceduresController(
        private val questionnaireService: QuestionnaireService,
        private val notificationService: NotificationService,
        private val notificationTemplateService: NotificationTemplateService,
        private val gameCharacterService: GameCharacterService,
        private val questionnaireRepository: QuestionnaireRepository,
        private val jwtUtil: JWTUtil
) {

    @PostMapping("/approve.do")
    @Transactional
    open fun approve(
            @RequestBody form: ApproveForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        val questionnaire = questionnaireRepository.findById(form.questionnaireId).get()
        questionnaireService.changeStatus(form.questionnaireId, QuestionnaireStatus.APPROVED)
        gameCharacterService.createCharacter(questionnaire)
        notificationService.addNotification(notificationTemplateService.questionnaireApproved(questionnaire.author!!.userId))
    }

    @PostMapping("/clarify.do")
    @Transactional
    open fun clarify(
            @RequestBody form: ClarifyForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        val questionnaire = questionnaireRepository.findById(form.questionnaireId).get()
        questionnaireService.changeStatus(form.questionnaireId, QuestionnaireStatus.ON_CLARIFICATION)
        notificationService.addNotification(notificationTemplateService.questionnaireClarifying(questionnaire.author!!.userId))
    }

    @PostMapping("/archive.do")
    @Transactional
    open fun archive(
            @RequestBody form: ArchiveForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        val questionnaire = questionnaireRepository.findById(form.questionnaireId).get()
        questionnaireService.changeStatus(form.questionnaireId, QuestionnaireStatus.ARCHIVED)
        notificationService.addNotification(notificationTemplateService.questionnaireArchived(questionnaire.author!!.userId))
    }

    data class ApproveForm(val questionnaireId: String = "")
    data class ClarifyForm(val questionnaireId: String = "")
    data class ArchiveForm(val questionnaireId: String = "")

}