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
        var target: ItemTemplate? = null,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var ingredients: List<WarehouseEntry> = mutableListOf(),

        @ManyToOne
        var dependantSkill: Skill? = null,

        var minSkillLvl: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe", orphanRemoval = true)
        var successChanceDependencies: List<SuccessChanceDependency> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null

)