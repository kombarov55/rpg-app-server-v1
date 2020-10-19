package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.QuestionnaireConverter
import ru.novemis.rpgapp.dto.game.questionnaire.dto.QuestionnaireDto
import ru.novemis.rpgapp.dto.game.questionnaire.form.QuestionnaireForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
open class QuestionnaireController(
        private val converter: QuestionnaireConverter,
        private val repository: QuestionnaireRepository,
        private val userAccountRepository: UserAccountRepository,
        private val gameRepository: GameRepository,
        private val questionnaireTemplateRepository: QuestionnaireTemplateRepository,
        private val jwtUtil: JWTUtil
) {

    @GetMapping("/questionnaire/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): QuestionnaireDto {
        return repository.findById(id).get().let { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/questionnaireTemplate/{questionnaire-template-id}/questionnaire")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @PathVariable("questionnaire-template-id") questionnaireTemplateId: String,
            @RequestBody form: QuestionnaireForm,
            @RequestHeader("authorization") jwtToken: String
    ) {
        val userAccount = jwtToken.substring("Bearer ".length)
                .let { jwtUtil.getUsernameFromToken(it).toLong() }
                .let { userAccountRepository.findByUserId(it) }!!
        val game = gameRepository.findById(gameId).get()
        val questionnaireTemplate = questionnaireTemplateRepository.findById(questionnaireTemplateId).get()

        converter.toDomain(form, game, userAccount, questionnaireTemplate).let { repository.save(it) }
    }

    @GetMapping("/game/{game-id}/questionnaire")
    @Transactional
    open fun findAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<QuestionnaireDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }
}