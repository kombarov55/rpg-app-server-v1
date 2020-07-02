package ru.novemis.rpgapp.domain.game.skill

import ru.novemis.rpgapp.domain.game.Game
import java.util.UUID
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
data class SkillCategory(
        @Id
        var id: String = UUID.randomUUID().toString(),

        var name: String = "",

        var description: String = "",

        var img: String = "",

        var complex: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var skills: List<Skill> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, mappedBy = "skillCategory")
        var spellSchools: List<SpellSchool> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null
)