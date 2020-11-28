package ru.novemis.rpgapp.domain.useraccount

import ru.novemis.rpgapp.domain.announcement.Announcement
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import java.util.*
import javax.persistence.*

@Entity
data class UserAccount(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var userId: Long = -1,

        var firstName: String = "",

        var lastName: String = "",

        var photo50Url: String = "",

        @Enumerated(EnumType.STRING)
        var role: UserAccountRole = UserAccountRole.VISITOR,

        @OneToOne(cascade = [CascadeType.ALL])
        var userAccountPreferences: UserAccountPreferences = UserAccountPreferences(),

        @OneToMany(cascade = [CascadeType.ALL])
        var announcements: List<Announcement> = emptyList(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "userAccount")
        var activityPoints: List<ActivityPoints> = emptyList(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "userAccount")
        var gameToActiveCharacter: List<GameToActiveCharacter> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner")
        var characters: List<GameCharacter> = emptyList()
)