package ru.novemis.rpgapp.domain.game.skill

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class UpgradeCost(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null,

        var lvlNum: Int = -1,

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "upgradeCost")
        var upgradeCostOptions: List<UpgradeCostOption> = emptyList()
)