package ru.novemis.rpgapp.domain.game.crafting

import ru.novemis.rpgapp.domain.game.shop.WarehouseEntry
import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.*
import javax.persistence.*

@Entity
data class Recipe (

        @Id
        val id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var ingredients: List<WarehouseEntry> = mutableListOf(),

        @OneToOne
        var dependantSkill: Skill? = null,

        var minSkillLevel: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe", orphanRemoval = true)
        var successChanceDependencies: List<SuccessChanceDependency> = mutableListOf()

)