package ru.novemis.rpgapp.domain.useraccount

import ru.novemis.rpgapp.domain.announcement.Announcement
import java.util.*
import javax.persistence.*

@Entity
data class UserAccountPreferences(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToOne
        var userAccount: UserAccount? = null,

        @ManyToMany
        @JoinTable(
                name = "user_account_preferences__favorite__announcement",
                joinColumns = [JoinColumn(name = "user_account_preferences_id")],
                inverseJoinColumns = [JoinColumn(name = "announcement_id")]
        )
        var favoriteAnnouncements: List<Announcement> = emptyList(),

        @ManyToMany
        @JoinTable(
                name = "user_account_preferences__responded__announcements",
                joinColumns = [JoinColumn(name = "user_account_preferences_id")],
                inverseJoinColumns = [JoinColumn(name = "announcement_id")]
        )
        var respondedAnnouncements: List<Announcement> = emptyList()
)