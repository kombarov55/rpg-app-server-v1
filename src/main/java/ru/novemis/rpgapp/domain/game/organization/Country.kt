package ru.novemis.rpgapp.domain.game.organization

import ru.novemis.rpgapp.domain.game.Game
import ru.novemis.rpgapp.domain.game.common.Price
import ru.novemis.rpgapp.domain.game.shop.Shop
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import java.util.*
import javax.persistence.Entity

@Entity
open class Country(
        id: String = UUID.randomUUID().toString(),
        name: String = "",
        description: String = "",
        type: OrganizationType? = null,
        organizationHeads: List<UserAccount> = mutableListOf(),
        initialBudget: List<Price> = mutableListOf(),
        shops: List<Shop> = mutableListOf(),
        game: Game? = null
) : Organization(id, name, description, type, organizationHeads, initialBudget, shops, game)