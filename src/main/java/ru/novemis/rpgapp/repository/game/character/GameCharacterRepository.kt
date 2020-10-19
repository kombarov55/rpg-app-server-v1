package ru.novemis.rpgapp.repository.game.character

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.character.GameCharacter

interface GameCharacterRepository : CrudRepository<GameCharacter, String> {
}