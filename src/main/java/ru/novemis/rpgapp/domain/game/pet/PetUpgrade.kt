package ru.novemis.rpgapp.domain.game.pet

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.domain.game.shop.SkillInfluence
import java.util.*
import javax.persistence.*

@Entity
class PetUpgrade(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "pet_id")
        var petTemplate: PetTemplate = PetTemplate(),

        var lvl: Int = 0,

        var description: String? = null,

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<PriceCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL])
        var skillInfluences: MutableList<SkillInfluence> = mutableListOf()
)