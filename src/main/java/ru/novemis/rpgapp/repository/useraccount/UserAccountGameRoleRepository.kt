package ru.novemis.rpgapp.repository.useraccount

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.useraccount.UserAccountGameRole

interface UserAccountGameRoleRepository : CrudRepository<UserAccountGameRole, String>