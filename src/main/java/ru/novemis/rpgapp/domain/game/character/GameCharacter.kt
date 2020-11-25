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
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.*

@Entity
class GameCharacter(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        @JoinColumn(name = "balance_id")
        var balance: Balance? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "character")
        var fieldToValueList: List<FieldToValue> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "character")
        var learnedSkills: List<SkillToLvl> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var items: List<Item> = mutableListOf(),

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

        var activityPoints: Int = 0
) {
        fun removeItem(itemTemplate: ItemTemplate) {
                items = items.filter { it.itemTemplate!!.id != itemTemplate.id }
        }

        fun addItem(itemTemplate: ItemTemplate) {
                items += itemTemplate.generateItem()
        }
}