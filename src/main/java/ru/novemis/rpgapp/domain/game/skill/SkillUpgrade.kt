package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.common.PriceCombination
import java.util.UUID
import javax.persistence.*

@Entity
data class SkillUpgrade(

        @Id
        var id: String = UUID.randomUUID().toString(),

        var lvlNum: Int = -1,

        @Column(columnDefinition = "TEXT")
        var description: String? = "",

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<PriceCombination> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "skill_id")
        var skill: Skill? = null
)