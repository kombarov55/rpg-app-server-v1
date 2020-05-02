package ru.novemis.rpgapp.domain.conversation

import org.hibernate.annotations.OrderBy
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
data class Conversation(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        var user1: UserAccount? = null,

        @OneToOne
        var user2: UserAccount? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "conversation")
        @OrderBy(clause = "creationDate DESC")
        var messages: List<Message> = emptyList()
)