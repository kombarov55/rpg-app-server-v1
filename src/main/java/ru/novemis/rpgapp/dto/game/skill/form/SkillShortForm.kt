package ru.novemis.rpgapp.dto.game.skill.form

import ru.novemis.rpgapp.domain.game.shop.Destination

data class SkillShortForm(
        val id: String = "",
        val name: String = "",
        val img: String = "",
        val destination: Destination = Destination.PLAYER
)