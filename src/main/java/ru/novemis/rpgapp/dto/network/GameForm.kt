package ru.novemis.rpgapp.dto.network

data class GameForm(
        var title: String = "",
        var description: String = "",
        var imgSrc: String = "",
        var networkId: String? = null,
        var subnetworkId: String? = null
)