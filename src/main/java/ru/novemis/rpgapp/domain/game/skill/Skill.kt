package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Currency
import ru.novemis.rpgapp.domain.game.Game
import java.util.*
import javax.persistence.*

@Entity
data class Skill(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var imgSrc: String = "",

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
        @JoinColumn(name = "game_id")
        var game: Game? = null
)