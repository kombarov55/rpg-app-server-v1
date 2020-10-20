package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.character.GameCharacterStatus
import ru.novemis.rpgapp.domain.useraccount.GameToActiveCharacter
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import java.util.*
import javax.transaction.Transactional

@RestController
open class UserAccountProceduresController(
        private val jwtUtil: JWTUtil,
        private val repository: UserAccountRepository,
        private val gameCharacterRepository: GameCharacterRepository
) {

    data class MakeCharacterActiveForm(val characterId: String = "", val gameId: String = "")
    @PostMapping("/userAccount/makeCharacterActive.do")
    @Transactional
    open fun makeCharacterActive(
            @RequestBody form: MakeCharacterActiveForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        val userAccount = repository.findByUserId(jwtUtil.getUserIdFromRawToken(jwtToken))!!

        val updatedEntity = (userAccount.gameToActiveCharacter.find { it.game!!.id == form.gameId }
                ?: GameToActiveCharacter(game = Game(id = form.gameId)))
                .apply { character = GameCharacter(id = form.characterId) }

        userAccount.gameToActiveCharacter = userAccount.gameToActiveCharacter.filter { it.game!!.id != form.gameId } + updatedEntity

        repository.save(userAccount)
    }


    data class KillCharacterForm(val characterId: String = "")
    @PostMapping("/userAccount/killCharacter.do")
    @Transactional
    open fun killCharacter(
            @RequestBody form: KillCharacterForm
    ) {
        gameCharacterRepository.findById(form.characterId).get()
                .apply {
                    status = GameCharacterStatus.DEAD
                    statusChangeDate = Date()
                }.also { gameCharacterRepository.save(it) }
    }

    data class ReviveCharacterForm(val characterId: String = "")
    @PostMapping("/userAccount/reviveCharacter.do")
    @Transactional
    open fun reviveCharacter(
            @RequestBody form: ReviveCharacterForm
    ) {
        gameCharacterRepository.findById(form.characterId).get()
                .apply {
                    status = GameCharacterStatus.ALIVE
                    statusChangeDate = Date()
                }.also { gameCharacterRepository.save(it) }
    }

}