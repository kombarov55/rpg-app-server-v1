package ru.novemis.rpgapp.controller.view

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.GameCharacterConverter
import ru.novemis.rpgapp.converter.GameConverter
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.dto.view.GameToCharacters
import ru.novemis.rpgapp.dto.view.OfficeViewDto
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
@RequestMapping("/officeView")
class OfficeViewController(
    private val jwtUtil: JWTUtil,
    private val userAccountRepository: UserAccountRepository,
    private val userAccountConverter: UserAccountConverter,
    private val gameConverter: GameConverter,
    private val gameCharacterConverter: GameCharacterConverter
) {

    @GetMapping
    @Transactional
    fun getPageDto(@RequestHeader("Authorization") jwtToken: String): OfficeViewDto {
        val userId = jwtUtil.getUserIdFromRawToken(jwtToken)
        val userAccount = userAccountRepository.findByUserId(userId)!!
        val charactersByGame = userAccount.characters.groupBy { character -> character.game!! }

        return OfficeViewDto(
            userAccount = userAccountConverter.toDto(userAccount),
            gamesToCharacters = charactersByGame.map { (k, v) ->
                GameToCharacters(
                    game = gameConverter.toShortDto(k),
                    characters = v.map { gameCharacterConverter.toRoleDto(it) }
                )
            }
        )
    }
}