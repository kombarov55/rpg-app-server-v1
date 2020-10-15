package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

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

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
        var shops: List<Shop> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var ownedMerchandise: List<WarehouseEntry> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)