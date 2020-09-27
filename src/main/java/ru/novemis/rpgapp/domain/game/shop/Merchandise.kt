package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.UUID
import javax.persistence.*

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
        var price: List<PriceCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var skillInfluences: List<SkillInfluence> = mutableListOf(),

        var destination: Destination? = null,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var merchandiseUpgrades: List<MerchandiseUpgrade> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)