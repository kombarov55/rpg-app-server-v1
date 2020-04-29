package ru.novemis.rpgapp.domain.announcement

import org.hibernate.annotations.GenericGenerator
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
data class Announcement(
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        var id: String = "",

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

        val commentsEnabled: Boolean = false
)