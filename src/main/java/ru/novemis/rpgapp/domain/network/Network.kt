package ru.novemis.rpgapp.domain.network

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Network(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = ""
)