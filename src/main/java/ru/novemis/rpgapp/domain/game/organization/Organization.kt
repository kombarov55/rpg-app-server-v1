package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.shop.Merchandise
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.domain.useraccount.UserAccount
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
                name = "organization__user_account",
                joinColumns = [JoinColumn(name = "organization_id")],
                inverseJoinColumns = [JoinColumn(name = "user_account_id")]
        )
        var organizationHeads: List<UserAccount> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var balance: List<Price> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization", orphanRemoval = true)
        var shops: List<Shop> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var ownedMerchandise: List<WarehouseEntry> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)