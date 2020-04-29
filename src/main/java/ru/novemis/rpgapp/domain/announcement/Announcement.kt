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
        var id: String? = null,

        @ManyToOne
        var author: UserAccount? = null,

        var creationDate: Date? = null,

        val title: String? = null,

        val gameType: GameType? = null,

        val sex: Sex? = null,

        val minAge: Int? = null,

        val maxAge: Int? = null,

        @Column(columnDefinition = "TEXT")
        val description: String? = null,

        val anonymous: Boolean? = null,

        val commentsEnabled: Boolean? = null,

        val uploadUid: String? = null,

        @OneToMany(fetch = FetchType.EAGER)
        var images: List<ImageLink>? = null
)