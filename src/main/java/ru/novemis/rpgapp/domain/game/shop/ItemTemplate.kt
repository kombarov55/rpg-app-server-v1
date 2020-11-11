package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class ItemTemplate(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        val description: String? = "",

        var img: String = "",

        @ManyToOne
        var category: ItemCategory? = null,

        @ManyToOne
        var type: ItemType? = null,

        var slots: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var skillInfluences: List<SkillInfluence> = mutableListOf(),

        var destination: Destination? = null,

        var upgradable: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var upgrades: List<ItemUpgrade> = mutableListOf(),

        var canBeEquipped: Boolean = false,

        var canBeUsedInCraft: Boolean = false,

        var canBeCrafted: Boolean = false,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)