package ru.novemis.rpgapp.domain.game.common

import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class PriceCombination(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL])
        var prices: List<Price> = mutableListOf()
)