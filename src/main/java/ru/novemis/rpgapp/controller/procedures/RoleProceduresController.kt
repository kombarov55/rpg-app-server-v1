package ru.novemis.rpgapp.controller.procedures;

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.domain.useraccount.Role
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountGameRoleRepository
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.noarg
import javax.transaction.Transactional

@RestController
@RequestMapping("/role")
class RoleProceduresController(
    private val characterRepository: GameCharacterRepository,
    private val userAccountRepository: UserAccountRepository,
    private val userAccountGameRoleRepository: UserAccountGameRoleRepository
) {

    @noarg data class ChangeUserAccountRoleRq(
        val userAccountId: String,
        val newRole: Role
    )

    @PostMapping("/changeUserAccountRole.do")
    @Transactional
    fun changeUserAccountRole(@RequestBody rq: ChangeUserAccountRoleRq) {
        val userAccount = userAccountRepository.findById(rq.userAccountId).get()
        userAccount.role = rq.newRole
        userAccountRepository.save(userAccount)
    }

    @noarg data class ChangeUserAccountGameRoleRq(
        val userAccountId: String,
        val gameId: String,
        val newRole: Role
    )

    @PostMapping("/changeUserAccountGameRole.do")
    @Transactional
    fun changeUserAccountGameRole(@RequestBody rq: ChangeUserAccountGameRoleRq) {
        val userAccount = userAccountRepository.findById(rq.userAccountId).get()

        val roleInGame = userAccount.rolesInGames.find { it.game.id == rq.gameId }!!
        roleInGame.role = rq.newRole
        userAccountGameRoleRepository.save(roleInGame)
    }

    @noarg data class ChangeCharacterRoleRq(
        val characterId: String,
        val newRole: Role
    )

    @PostMapping("/changeCharacterRole.do")
    @Transactional
    fun changeCharacterRole(@RequestBody rq: ChangeCharacterRoleRq) {
        val character = characterRepository.findById(rq.characterId).get()
        character.role = rq.newRole
        characterRepository.save(character)
    }

}
