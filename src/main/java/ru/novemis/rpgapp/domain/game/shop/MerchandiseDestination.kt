package ru.novemis.rpgapp.domain.game.shop

enum class MerchandiseDestination(val value: String) {
    PLAYER("Для игроков"),
    COUNTRY("Для стран"),
    INSTITUTION("Для заведений"),
    HOUSE("Для жилых домов"),
    SHIP("Для кораблей"),
    MARKETPLACE("Для рынков")
}