package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.controller.OrganizationController
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.domain.game.questionnaire.SkillToLvl
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
class Organization(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        val type: OrganizationType = OrganizationType.INSTITUTION,

        @OneToMany(cascade = [CascadeType.ALL])
        val equippedItems: List<Item> = emptyList(),

        @ManyToMany
        @JoinTable(
                name = "organization__game_character",
                joinColumns = [JoinColumn(name = "organization_id")],
                inverseJoinColumns = [JoinColumn(name = "game_character_id")]
        )
        var heads: List<GameCharacter> = mutableListOf(),

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn(name = "balance_id")
        var balance: Balance? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
        var shops: List<Shop> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var items: List<Item> = mutableListOf(),

        var incomeTax: Double = 0.0,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "organization")
        var creditOffers: List<CreditOffer> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
) {
        fun applyPatch(patch: OrganizationController.OrganizationPatch): Organization {
                this.incomeTax = patch.incomeTax ?: this.incomeTax

                return this
        }
}