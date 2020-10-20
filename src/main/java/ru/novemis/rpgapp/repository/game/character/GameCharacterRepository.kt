package ru.novemis.rpgapp.repository.game.character

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import ru.novemis.rpgapp.domain.game.character.GameCharacter

interface GameCharacterRepository : PagingAndSortingRepository<GameCharacter, String> {

    fun findAllByGameId(gameId: String): List<GameCharacter>

    @Query("select gc from GameCharacter gc where gc.owner.userId = :userId")
    fun findByUserId(userId: Long): List<GameCharacter>

    fun findByGameIdAndNameStartsWith(gameId: String, name: String, pageable: Pageable): List<GameCharacter>

}