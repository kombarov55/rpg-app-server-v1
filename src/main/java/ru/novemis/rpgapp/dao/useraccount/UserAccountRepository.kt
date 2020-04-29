package ru.novemis.rpgapp.dao.useraccount

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.useraccount.UserAccount

interface UserAccountRepository : CrudRepository<UserAccount, String> {

    fun findByUserId(id: Int): UserAccount?

}