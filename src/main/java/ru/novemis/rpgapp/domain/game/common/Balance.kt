package ru.novemis.rpgapp.domain.game.common

import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Balance(
        @Id
        var id: String = UUID.randomUUID().toString(),

        @OneToMany(cascade = [CascadeType.ALL], mappedBy = "balance")
        var amounts: List<Price> = mutableListOf()
)