package ru.novemis.rpgapp.domain.conversation

import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Conversation(
        @Id
        @GeneratedValue
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        var user1: UserAccount? = null,

        @OneToOne
        var user2: UserAccount? = null
)