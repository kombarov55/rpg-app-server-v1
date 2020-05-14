package ru.novemis.rpgapp.domain.game.skill

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
data class UpgradeCostOption(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "upgradeCostOption")
        var upgradeCostOptionEntries: List<UpgradeCostOptionEntry> = emptyList(),

        @ManyToOne
        @JoinColumn(name = "upgrade_cost_id")
        var upgradeCost: UpgradeCost? = null
)