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

        var userId: Int? = null,

        var firstName: String? = null,

        var lastName: String? = null,

        var photo50Url: String? = null
)