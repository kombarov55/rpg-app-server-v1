package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.shop.Destination
import java.util.UUID
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
data class SkillCategory(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        @Column(columnDefinition = "TEXT")
        var description: String = "",

        var img: String = "",

        var complex: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var skills: List<Skill> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var spellSchools: List<SpellSchool> = mutableListOf(),

        var destination: Destination? = null,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)