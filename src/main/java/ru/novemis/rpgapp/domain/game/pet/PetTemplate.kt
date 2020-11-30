package ru.novemis.rpgapp.domain.game.pet

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.character.GameCharacter
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.domain.game.shop.SkillInfluence
import java.util.*
import javax.persistence.*

@Entity
class PetTemplate(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game = Game(),

        var name: String = "",

        var img: String = "",

        var description: String? = "",

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<PriceCombination> = mutableListOf(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "petTemplate")
        var upgrades: MutableList<PetUpgrade> = mutableListOf()
)