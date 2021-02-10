package ru.novemis.rpgapp.domain.game.character

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Balance
import ru.novemis.rpgapp.domain.game.organization.Credit
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.questionnaire.FieldToValue
import ru.novemis.rpgapp.domain.game.questionnaire.SkillToLvl
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.domain.game.shop.ItemTemplate
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.domain.useraccount.Role
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.dto.game.character.dto.SkillStatsDto
import java.util.*
import javax.persistence.*

@Entity
class GameCharacter(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var img: String = "",

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn(name = "balance_id")
        var balance: Balance? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "character")
        var fieldToValueList: List<FieldToValue> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "character")
        var learnedSkills: List<SkillToLvl> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var items: List<Item> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var equippedItems: List<Item> = emptyList(),

        @ManyToMany
        @JoinTable(
                name = "organization__game_character",
                joinColumns = [JoinColumn(name = "game_character_id")],
                inverseJoinColumns = [JoinColumn(name = "organization_id")]
        )
        var managingOrganizations: List<Organization> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "country_id")
        var country: Organization? = null,

        @ManyToMany
        @JoinTable(
                name = "game_character__spell",
                joinColumns = [JoinColumn(name = "game_character_id")],
                inverseJoinColumns = [JoinColumn(name = "spell_id")]
        )
        var learnedSpells: List<Spell> = mutableListOf(),

        val creationDate: Date = Date(),

        var status: GameCharacterStatus = GameCharacterStatus.ALIVE,

        var statusChangeDate: Date = Date(),

        @ManyToOne
        @JoinColumn(name = "user_account_id")
        var owner: UserAccount? = null,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null,

        @ManyToOne
        @JoinColumn(name = "questionniare_template_id")
        var questionnaireTemplate: QuestionnaireTemplate? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner")
        val credits: List<Credit> = mutableListOf(),

        var activityPoints: Int = 0,

        var role: Role = Role.MAIN_ADMIN
) {
    fun removeItem(itemTemplate: ItemTemplate) {
        items = items.filter { it.itemTemplate!!.id != itemTemplate.id }
    }

    fun removeItem(itemId: String): GameCharacter = apply {
        items = items.filter { it.id != itemId }
    }

    fun removeItem(item: Item): GameCharacter = apply {
        items = items.filter { it.id != item.id }
    }

    fun addItem(item: Item): GameCharacter {
        if (items.size + 1 <= game!!.settings.inventorySize) {
            items += item

            return this
        } else {
            throw RuntimeException("Инвентарь переполнен.")
        }
    }

    fun addItem(itemTemplate: ItemTemplate) = addItem(itemTemplate.generateItem())

    fun equipItem(item: Item): GameCharacter {
        if (!item.itemTemplate!!.canBeEquipped) {
            throw RuntimeException("Невозможно одеть предмет.")
        }

        val maxAmount = game!!.settings.maxEquippedAmounts.find {
            item.itemTemplate!!.category!!.id == it.itemCategory!!.id
        }?.amount ?: throw RuntimeException("Невозможно одеть предмет.")

        val equipppedAmount = equippedItems.filter {
            it.itemTemplate!!.category!!.id == item.itemTemplate!!.category!!.id
        }.size

        val itemAmount = item.itemTemplate!!.slots

        if (equipppedAmount + itemAmount > maxAmount) {
            throw RuntimeException("Невозможно одеть предмет.")
        }

        equippedItems += item
        removeItem(item)

        return this
    }

    fun unequipItem(item: Item): GameCharacter {
        equippedItems = equippedItems.filter { it.id != item.id }
        addItem(item)

        return this
    }

    fun calculateStats(): List<SkillStatsDto> {
        val itemSkillInfluences = equippedItems.flatMap { it.calculateSkillInfluence() }

        val usedSkills = game!!.skillCategories
                .filter { !it.complex }
                .flatMap { it.skills }
                .filter { skill ->
                    learnedSkills.any { it.skill!!.id == skill.id } ||
                    itemSkillInfluences.any { it.skill!!.id == skill.id }
                }

        return usedSkills.map { skill ->
            val initialAmount = learnedSkills.find { it.skill!!.id == skill.id }?.amount ?: 0
            val finalAmount = itemSkillInfluences.filter { it.skill!!.id == skill.id }
                                                 .fold(initialAmount) { acc, skillInfluence ->
                                                     skillInfluence.modifier!!.calculate(acc, skillInfluence.amount)
                                                 }

            SkillStatsDto(
                    skillName = skill.name,
                    initialAmount = initialAmount,
                    bonusAmount = finalAmount - initialAmount
            )
        }
    }
}