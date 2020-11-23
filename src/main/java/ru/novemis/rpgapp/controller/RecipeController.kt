package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.RecipeConverter
import ru.novemis.rpgapp.dto.game.crafting.dto.RecipeDto
import ru.novemis.rpgapp.dto.game.crafting.form.RecipeForm
import ru.novemis.rpgapp.repository.game.RecipeRepository
import javax.transaction.Transactional

@RestController
open class RecipeController(
        private val repository: RecipeRepository,
        private val converter: RecipeConverter
) {

    @GetMapping("/game/{game-id}/recipe")
    @Transactional
    open fun findAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<RecipeDto> = repository.findAllByGameId(gameId).map { converter.toDto(it) }

    @PostMapping("/game/{game-id}/recipe")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: RecipeForm
    ): RecipeDto {
        return converter.toDomain(form, gameId)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/recipe/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): RecipeDto {
        return repository.findById(id).get().also {
            repository.deleteById(id)
        }.let { converter.toDto(it) }
    }

}