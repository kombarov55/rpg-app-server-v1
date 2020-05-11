package ru.novemis.rpgapp.domain.network

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class SkillType(
        @Id
        var id: String = "",

        var name: String = "",

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)