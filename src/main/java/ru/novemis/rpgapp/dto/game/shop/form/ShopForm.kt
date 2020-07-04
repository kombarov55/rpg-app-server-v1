package ru.novemis.rpgapp.dto.game.shop.form

import ru.novemis.rpgapp.domain.game.shop.ShopType

data class ShopForm(
        var name: String = "",
        var img: String = "",
        var type: ShopType = ShopType.PLAYERS
)