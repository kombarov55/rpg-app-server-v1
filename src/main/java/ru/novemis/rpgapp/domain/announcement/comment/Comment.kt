package ru.novemis.rpgapp.domain.announcement.comment

import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Comment(
        @Id
        var id: String? = UUID.randomUUID().toString(),

        @ManyToOne
        var author: UserAccount? = null,

        @ManyToOne
        var announcement: Announcement? = null,

        var creationDate: Date = Date(),

        @Column(columnDefinition = "TEXT")
        var text: String = "",

        var deleted: Boolean = false
)