package ru.novemis.rpgapp.domain.game.shop

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class MerchandiseUpgrade(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var lvlNum: Int = 0,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        var skillInfluences: List<SkillInfluence> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
        val prices: List<PriceCombination> = mutableListOf()

)