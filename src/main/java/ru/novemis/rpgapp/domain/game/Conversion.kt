package ru.novemis.rpgapp.domain.game

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Conversion(

        @Id
        var id: String = UUID.randomUUID().toString(),

        @ManyToOne
        @JoinColumn(name = "currency1_id")
        var currency1: Currency? = null,

        @ManyToOne
        @JoinColumn(name = "currency2_id")
        var currency2: Currency? = null,

        var conversionPrice1to2: Double = .0,

        var conversionPrice2to1: Double = .0,

        @ManyToOne
        @JoinColumn(name = "game_id")
        var game: Game? = null

)