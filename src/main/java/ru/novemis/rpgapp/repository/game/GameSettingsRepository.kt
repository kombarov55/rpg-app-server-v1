package ru.novemis.rpgapp.repository.game

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.GameSettings

interface GameSettingsRepository : CrudRepository<GameSettings, String> {
    fun findByGameId(gameId: String): GameSettings
}