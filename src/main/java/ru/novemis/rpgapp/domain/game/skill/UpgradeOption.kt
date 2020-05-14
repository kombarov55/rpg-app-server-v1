package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.skill.Skill
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity
class UpgradeOption(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null,

        @ManyToMany
        @JoinTable(
                name = "upgrade_option__currencies",
                joinColumns = [JoinColumn(name = "upgrade_option_id")],
                inverseJoinColumns = [JoinColumn(name = "currency_id")]
        )
        var currencies: List<Currency> = emptyList()
)