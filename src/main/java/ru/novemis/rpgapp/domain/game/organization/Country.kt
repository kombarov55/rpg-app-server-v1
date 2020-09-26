package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.common.PriceCombination
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
open class Country(
        id: String = UUID.randomUUID().toString(),
        name: String = "",
        description: String = "",
        type: OrganizationType? = null,
        organizationHeads: List<UserAccount> = mutableListOf(),
        initialBudget: List<Price> = mutableListOf(),
        shops: List<Shop> = mutableListOf(),
        game: Game? = null,

        @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
        val entranceTax: PriceCombination? = null,

        val incomeTax: Double? = null,

        @OneToMany(cascade = [CascadeType.ALL])
        var creditOffers: List<CreditOffer> = mutableListOf()
) : Organization(id, name, description, type, organizationHeads, initialBudget, shops, game)