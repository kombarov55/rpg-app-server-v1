package ru.novemis.rpgapp.repository.useraccount

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.useraccount.UserAccount

interface UserAccountRepository : CrudRepository<UserAccount, String> {

    fun findByUserId(id: Long): UserAccount?

    @Query("select u from UserAccount u where lower(u.firstName) like concat(lower(:name), '%')  or lower(u.lastName) like concat(lower(:name), '%')")
    fun searchByNameOrCharacterName(name: String): List<UserAccount>

    @Query("select gc.owner from GameCharacter gc where lower(gc.name) like concat(lower(:name), '%')")
    fun searchByCharacterName(name: String): List<UserAccount>
}