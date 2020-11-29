package ru.novemis.rpgapp.domain.game.shop

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Item(
        @Id
        val id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "item_template_id")
        val itemTemplate: ItemTemplate? = null,

        var lvl: Int = 0
) {
    fun upgrade(): Item = apply {
        lvl += 1
    }

    fun getCurrentLvl(): ItemUpgrade = itemTemplate!!.upgrades.find { it.lvlNum == lvl }!!
    fun getNextLvl(): ItemUpgrade? = itemTemplate!!.upgrades.find { it.lvlNum == lvl + 1 }

    fun calculateSkillInfluence(): List<SkillInfluence> =
            if (lvl == 0)
                itemTemplate!!.skillInfluences else
                itemTemplate!!.upgrades.find { it.lvlNum == lvl }!!.skillInfluences
}