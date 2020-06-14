package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany


@Entity
data class Skill(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var img: String = "",

        var description: String = "",

        var price: Int = -1,

        var upgradable: Boolean = false,

        @ManyToMany
        @JoinTable(
                name = "skill__currency_for_upgrade",
                joinColumns = [JoinColumn(name = "skill_id")],
                inverseJoinColumns = [JoinColumn(name = "currency_id")]
        )
        var currenciesForUpgrade: List<Currency> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        var skillUpgradeCurrencyCombinations: List<SkillUpgradeCurrencyCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        val skillUpgrades: List<SkillUpgrade> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_category_id")
        var skillCategory: SkillCategory? = null
)