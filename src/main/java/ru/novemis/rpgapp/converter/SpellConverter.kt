package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.skill.Spell
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto

@Component
class SpellConverter {

    fun toDto(domain: Spell): SpellDto {
        return SpellDto(
                id = domain.id,
                img = domain.img,
                name = domain.name,
                description = domain.description
        )
    }

}