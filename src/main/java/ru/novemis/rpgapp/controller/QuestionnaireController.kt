 package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.QuestionnaireConverter
import ru.novemis.rpgapp.dto.game.questionnaire.form.QuestionnaireForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.questionnaire.QuestionnaireRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
open class QuestionnaireController(
        private val converter: QuestionnaireConverter,
        private val repository: QuestionnaireRepository,
        private val userAccountRepository: UserAccountRepository,
        private val gameRepository: GameRepository,
        private val jwtUtil: JWTUtil
) {

    @PostMapping("/game/{game-id}/questionnaire")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: QuestionnaireForm,
            @RequestHeader("authorization") jwtToken: String
    ) {
        val userAccount = jwtToken
                .let { jwtUtil.getUsernameFromToken(it).toLong() }
                .let { userAccountRepository.findByUserId(it) }!!
        val game = gameRepository.findById(gameId).get()

        converter.toDomain(form, game, userAccount).let { repository.save(it) }
    }
}