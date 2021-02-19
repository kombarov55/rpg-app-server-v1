package ru.novemis.rpgapp.controller.procedures

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.domain.game.character.GameCharacterStatus
import ru.novemis.rpgapp.service.GameCharacterService
import ru.novemis.rpgapp.service.UserAccountService
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
@RequestMapping("/userAccount")
open class UserAccountProceduresController(
        private val userAccountService: UserAccountService,
        private val gameCharacterService: GameCharacterService,
        private val jwtUtil: JWTUtil
) {

    data class MakeCharacterActiveForm(val characterId: String = "", val gameId: String = "")

    @PostMapping("/makeCharacterActive.do")
    @Transactional
    open fun makeCharacterActive(
            @RequestBody form: MakeCharacterActiveForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        userAccountService.makeCharacterActive(jwtUtil.getUserIdFromRawToken(jwtToken), form.characterId, form.gameId)
    }


    data class KillCharacterForm(val characterId: String = "", val gameId: String = "")

    @PostMapping("/killCharacter.do")
    @Transactional
    open fun killCharacter(
            @RequestBody form: KillCharacterForm,
            @RequestHeader("Authorization") jwtToken: String
    ) {
        gameCharacterService.changeStatus(form.characterId, GameCharacterStatus.DEAD)
        userAccountService.removeActiveCharacter(jwtUtil.getUserIdFromRawToken(jwtToken), form.gameId)
    }

    data class ReviveCharacterForm(val characterId: String = "")

    @PostMapping("/reviveCharacter.do")
    @Transactional
    open fun reviveCharacter(
            @RequestBody form: ReviveCharacterForm
    ) {
        gameCharacterService.changeStatus(form.characterId, GameCharacterStatus.ALIVE)
    }

}