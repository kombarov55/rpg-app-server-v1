package ru.novemis.rpgapp.domain.network

import java.util.*
import javax.persistence.*

@Entity
data class Game(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgSrc: String = "",

        @ManyToOne
        @JoinColumn(name = "network_id")
        var network: Network? = null,

        @ManyToOne
        @JoinColumn(name = "subnetwork_id")
        var subnetwork: Subnetwork? = null
)