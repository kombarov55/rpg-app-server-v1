package ru.novemis.rpgapp.domain.network

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Subnetwork(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        var description: String = "",

        @ManyToOne
        @JoinColumn(name = "network_id")
        var network: Network? = null
)