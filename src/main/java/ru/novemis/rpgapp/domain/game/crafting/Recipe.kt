package ru.novemis.rpgapp.domain.game.crafting

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.shop.ItemTemplate
import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.*
import javax.persistence.*

@Entity
data class Recipe (

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "target_id")
        var target: ItemTemplate? = null,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe")
        var ingredients: List<ItemTemplateAmount> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "dependant_skill_id")
        var dependantSkill: Skill? = null,

        var minSkillLvl: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe")
        var successChanceDependencies: List<SuccessChanceDependency> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null

)