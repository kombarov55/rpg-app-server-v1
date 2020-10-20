package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.useraccount.GameToActiveCharacter
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
open class UserAccountProceduresController(
        private val jwtUtil: JWTUtil,
        private val repository: UserAccountRepository
) {

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


    data class MakeCharacterActiveForm(val characterId: String = "", val gameId: String = "")
}