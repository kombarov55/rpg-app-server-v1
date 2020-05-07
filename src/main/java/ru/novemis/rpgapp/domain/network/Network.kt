package ru.novemis.rpgapp.domain.network

import java.util.*
import javax.persistence.*

@Entity
data class Network(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "network")
        var subnetworks: List<Subnetwork> = emptyList()
)