package ru.novemis.rpgapp.domain.network

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class Network(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgName: String = "",

        var backgroundImgName: String = "",

        var groupLink: String = "",

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "network")
        var subnetworks: List<Subnetwork> = emptyList(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "network")
        var games: List<Game> = emptyList(),

        var deleted: Boolean = false,

        var deletionDate: Date? = null
)