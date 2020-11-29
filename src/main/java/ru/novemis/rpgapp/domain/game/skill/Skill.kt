package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.UUID
import javax.persistence.*

@Entity
data class Skill(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var img: String = "",

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var prices: List<PriceCombination> = mutableListOf(),

        var upgradable: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        var upgrades: List<SkillUpgrade> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null
) {
        fun setPrices(prices: List<PriceCombination>): Skill {
                val thisPrices = this.prices as MutableList
                thisPrices.clear()
                thisPrices.addAll(prices)
                return this
        }
}
