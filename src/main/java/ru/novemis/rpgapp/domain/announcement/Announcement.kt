package ru.novemis.rpgapp.domain.announcement

import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Announcement(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        var author: UserAccount? = null,

        var creationDate: Date = Date(),

        val title: String = "",

        val gameType: GameType? = null,

        val sex: Sex? = null,

        val minAge: Int? = null,

        val maxAge: Int? = null,

        @Column(columnDefinition = "TEXT")
        val description: String = "",

        val anonymous: Boolean = false,

        val commentsEnabled: Boolean = false,

        var deleted: Boolean = false
)