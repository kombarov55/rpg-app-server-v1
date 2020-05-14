package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.questionnaire.Questionnaire
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
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

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        @ManyToOne
        @JoinColumn(name = "skill_type_id")
        var skillType: SkillType? = null,

        @ManyToMany
        @JoinTable(
                name = "skill__currencies_for_upgrade",
                joinColumns = [JoinColumn(name = "skill_id")],
                inverseJoinColumns = [JoinColumn(name = "currency_id")]
        )
        var currenciesForUpgrade: List<Currency> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        var upgradeOptions: List<UpgradeOption> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "skill")
        var upgradeCosts: List<UpgradeCost> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "questionnaire_id")
        var questionnaire: Questionnaire? = null
)