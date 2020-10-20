package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.useraccount.GameToActiveCharacter
import ru.novemis.rpgapp.dto.game.character.dto.GameCharacterShortDto
import ru.novemis.rpgapp.dto.game.dto.GameShortDto
import ru.novemis.rpgapp.dto.useraccount.dto.GameToActiveCharacterDto

@Component
class GameToActiveCharacterConverter{

    fun toDto(domain: GameToActiveCharacter): GameToActiveCharacterDto {
        return GameToActiveCharacterDto(
                id = domain.id,
                game = GameShortDto(
                        id = domain.game!!.id,
                        name = domain.game!!.title,
                        img = domain.game!!.imgName
                ),
                activeCharacter = GameCharacterShortDto(
                        id = domain.character!!.id,
                        name = domain.character!!.name
                )
        )
    }

}