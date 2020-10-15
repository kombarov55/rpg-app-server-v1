package ru.novemis.rpgapp.dto.game.skill.dto

import ru.novemis.rpgapp.domain.game.shop.Destination

data class SkillShortDto(
        val id: String,
        val name: String,
        val img: String,
        val destination: Destination
)