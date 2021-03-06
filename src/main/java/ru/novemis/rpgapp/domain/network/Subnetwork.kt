package ru.novemis.rpgapp.domain.network

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class Subnetwork(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgName: String = "",

        var backgroundImgName: String = "",

        var groupLink: String = "",

        @ManyToOne
        @JoinColumn(name = "network_id")
        var network: Network? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "subnetwork")
        var games: List<Game> = emptyList(),

        var deleted: Boolean = false,

        var deletionDate: Date? = null
)