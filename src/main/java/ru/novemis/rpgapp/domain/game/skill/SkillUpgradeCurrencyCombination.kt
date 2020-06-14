package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
data class SkillUpgradeCurrencyCombination(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToMany
        @JoinTable(
                name = "skill_upgrade_currency_combination__currency",
                joinColumns = [JoinColumn(name = "skill_upgrade_currency_combination_id")],
                inverseJoinColumns = [JoinColumn(name = "currency_id")]
        )
        var currencies: List<Currency> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null

)