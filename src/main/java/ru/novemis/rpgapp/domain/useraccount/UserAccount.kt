package ru.novemis.rpgapp.domain.useraccount

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class UserAccount(
        @Id
        var id: String? = UUID.randomUUID().toString(),

        var userId: Long = -1,

        var firstName: String = "",

        var lastName: String = "",

        var photo50Url: String = "",

        var role: UserAccountRole = UserAccountRole.VISITOR,

        @OneToOne(cascade = [CascadeType.ALL])
        var userAccountPreferences: UserAccountPreferences = UserAccountPreferences()
)