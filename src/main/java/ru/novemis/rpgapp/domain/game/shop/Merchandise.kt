package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.Game
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class Merchandise(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        val description: String? = "",

        var img: String = "",

        @ManyToOne
        var category: MerchandiseCategory? = null,

        @ManyToOne
        var type: MerchandiseType? = null,

        var slots: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var skillInfluences: List<SkillInfluence> = mutableListOf(),

        var destination: Destination? = null,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var upgrades: List<MerchandiseUpgrade> = mutableListOf(),

        var canBeEquipped: Boolean = false,

        var canBeUsedInCraft: Boolean = false,

        var canBeCrafted: Boolean = false,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)