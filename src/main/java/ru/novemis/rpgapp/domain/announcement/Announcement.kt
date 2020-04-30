package ru.novemis.rpgapp.domain.announcement

import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
data class Announcement(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "author_id")
        var author: UserAccount? = null,

        var creationDate: Date = Date(),

        val title: String = "",

        @Enumerated(EnumType.STRING)
        val gameType: GameType? = null,

        @Enumerated(EnumType.STRING)
        val sex: Sex? = null,

        val minAge: Int? = null,

        val maxAge: Int? = null,

        @Column(columnDefinition = "TEXT")
        val description: String = "",

        val anonymous: Boolean = false,

        val commentsEnabled: Boolean = false,

        var deleted: Boolean = false
)