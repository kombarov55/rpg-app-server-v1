package ru.novemis.rpgapp.domain.announcement.comment

import org.hibernate.annotations.GenericGenerator
import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
data class Comment(
        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        var id: String? = null,

        @ManyToOne
        var author: UserAccount? = null,

        @ManyToOne
        var announcement: Announcement? = null,

        var creationDate: Date = Date(),

        @Column(columnDefinition = "TEXT")
        var text: String = ""
)