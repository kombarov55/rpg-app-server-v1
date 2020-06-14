package ru.novemis.rpgapp.domain.game.skill

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class UpgradeOption(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany
        var upgradeCosts: List<UpgradeCost> = mutableListOf()
)