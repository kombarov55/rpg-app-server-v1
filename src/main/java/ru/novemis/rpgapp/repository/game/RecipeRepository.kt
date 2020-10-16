package ru.novemis.rpgapp.repository.game

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.crafting.Recipe

interface RecipeRepository : CrudRepository<Recipe, String> {

    fun findAllByGameId(gameId: String): List<Recipe>

}