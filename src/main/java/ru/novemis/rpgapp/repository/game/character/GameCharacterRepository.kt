package ru.novemis.rpgapp.repository.game.character

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.character.GameCharacter

interface GameCharacterRepository : CrudRepository<GameCharacter, String> {

    @Query("select gc from GameCharacter gc where gc.owner.userId = :userId")
    fun findByUserId(userId: Long): List<GameCharacter>

}