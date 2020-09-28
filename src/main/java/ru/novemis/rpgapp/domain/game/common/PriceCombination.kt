package ru.novemis.rpgapp.domain.game.common

import java.util.*
import javax.persistence.*

@Entity
class PriceCombination(

        @Id
        var id: String? = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<Price> = mutableListOf()
)