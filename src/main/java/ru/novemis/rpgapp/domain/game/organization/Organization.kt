package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.controller.OrganizationController
import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.shop.Destination
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.dto.game.character.dto.SkillStatsDto
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
    fun applyPatch(patch: OrganizationController.OrganizationPatch): Organization = apply {
        this.incomeTax = patch.incomeTax ?: this.incomeTax
    }

    fun equipItem(itemId: String): Organization = apply {
        val item = items.find { it.id == itemId }!!
        removeItem(itemId)
        (equippedItems as MutableList).add(item)
    }

    fun unequipItem(itemId: String): Organization = apply {
        val item = equippedItems.find { it.id == itemId }!!
        (equippedItems as MutableList).removeIf { it.id == itemId }
        addItem(item)
    }

    fun addItem(item: Item) : Organization = apply {
        (items as MutableList).add(item)
    }

    fun removeItem(itemId: String): Organization = apply {
        (items as MutableList).removeIf { it.id == itemId }
    }

    fun calculateSkillStats(): List<SkillStatsDto> {
        val skillInfluences = equippedItems.flatMap { it.calculateSkillInfluence() }
        val skills = game!!.skillCategories.filter { !it.complex && it.destination!!.name == type.name }
                                           .flatMap { it.skills }

        return skills.map { skill ->
            val finalAmount = skillInfluences.filter { it.skill!!.id == skill.id }
                                             .fold(0) { acc, skillInfluence ->
                                                 skillInfluence.modifier!!.calculate(acc, skillInfluence.amount)
                                             }

            SkillStatsDto(
                    skillName = skill.name,
                    initialAmount = 0,
                    bonusAmount = finalAmount
            )
        }
    }


}