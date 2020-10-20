package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
open class Organization(
        @Id
        var id: String = UUID.randomUUID().toString(),

        val name: String = "",

        val description: String = "",

        val type: OrganizationType? = null,

        @ManyToMany
        @JoinTable(
                name = "organization__game_character",
                joinColumns = [JoinColumn(name = "organization_id")],
                inverseJoinColumns = [JoinColumn(name = "game_character_id")]
        )
        var organizationHeads: List<GameCharacter> = mutableListOf(),

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn(name = "balance_id")
        var balance: Balance? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
        var shops: List<Shop> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var ownedMerchandise: List<WarehouseEntry> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)