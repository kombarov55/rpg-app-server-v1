package ru.novemis.rpgapp.domain.conversation

import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
data class Message(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "conversation_id")
        var conversation: Conversation? = null,

        @ManyToOne
        @JoinColumn(name = "author_id")
        var author: UserAccount? = null,

        var creationDate: Date = Date(),

        @Column(columnDefinition = "TEXT")
        var text: String = ""
)