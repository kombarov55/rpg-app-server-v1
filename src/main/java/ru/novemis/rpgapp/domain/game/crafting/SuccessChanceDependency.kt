package ru.novemis.rpgapp.domain.game.crafting

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class SuccessChanceDependency(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var min: Int = 0,

        var max: Int = 0,

        var percent: Int = 0,

        @ManyToOne
        @JoinColumn(name = "receipe_id")
        var recipe: Recipe? = null
)