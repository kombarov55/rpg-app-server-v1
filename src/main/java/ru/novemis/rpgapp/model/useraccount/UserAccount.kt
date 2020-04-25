package ru.novemis.rpgapp.model.useraccount

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class UserAccount(
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        var id: String? = null,

        var userId: Int = -1,

        var firstName: String = "",

        var lastName: String = "",

        var photo50Url: String = "",

        var role: UserAccountRole = UserAccountRole.VISITOR
)