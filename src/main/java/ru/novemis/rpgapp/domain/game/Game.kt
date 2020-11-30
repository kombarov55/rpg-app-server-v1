package ru.novemis.rpgapp.domain.game

import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.domain.game.organization.Organization
import ru.novemis.rpgapp.domain.game.pet.PetTemplate
import ru.novemis.rpgapp.domain.game.questionnaire_template.QuestionnaireTemplate
import ru.novemis.rpgapp.domain.game.shop.Item
import ru.novemis.rpgapp.domain.game.shop.ItemForSale
import ru.novemis.rpgapp.domain.game.skill.SkillCategory
import ru.novemis.rpgapp.domain.network.Network
import ru.novemis.rpgapp.domain.network.Subnetwork
import ru.novemis.rpgapp.dto.game.pet.dto.PetTemplateDto
import java.util.*
import javax.persistence.*

@Entity
data class Game(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var title: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgName: String = "",

        var backgroundName: String = "",

        var groupLink: String = "",

        @ManyToOne
        @JoinColumn(name = "network_id")
        var network: Network? = null,

        @ManyToOne
        @JoinColumn(name = "subnetwork_id")
        var subnetwork: Subnetwork? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var currencies: List<Currency> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var conversions: List<Conversion> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var skillCategories: List<SkillCategory> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var recipes: List<Recipe> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var itemsForSale: List<ItemForSale> = emptyList(),

        @OneToMany(cascade = [CascadeType.ALL])
        var itemsSale: List<Item> = emptyList(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game", orphanRemoval = true)
        var questionnaireTemplates: List<QuestionnaireTemplate> = mutableListOf(),

        var deleted: Boolean = false,

        var deletionDate: Date? = null,

        @OneToMany(mappedBy = "game", cascade = [CascadeType.ALL])
        var organizations: List<Organization> = emptyList(),

        @OneToOne(cascade = [CascadeType.ALL], mappedBy = "game")
        var settings: GameSettings = GameSettings(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "game")
        var petTemplates: List<PetTemplate> = emptyList()
)