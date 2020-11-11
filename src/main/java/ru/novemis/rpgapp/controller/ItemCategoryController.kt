package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.ItemCategoryConverter
import ru.novemis.rpgapp.dto.game.shop.dto.ItemCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.ItemCategoryForm
import ru.novemis.rpgapp.repository.game.shop.ItemCategoryRepository

@RestController
class ItemCategoryController(
        private val repository: ItemCategoryRepository,
        private val converter: ItemCategoryConverter
) {

    @GetMapping("/game/{game-id}/itemCategory")
    fun getByGameId(
            @PathVariable("game-id") gameId: String
    ): List<ItemCategoryDto> = repository.findByGameId(gameId).map { converter.toDto(it) }

    @PostMapping("/game/{game-id}/itemCategory")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody itemCategoryForm: ItemCategoryForm
    ): ItemCategoryDto = itemCategoryForm
            .let { converter.toDomain(itemCategoryForm, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/game/{game-id}/itemCategory/{category-id}")
    fun delete(
            @PathVariable("game-id") gameId: String,
            @PathVariable("category-id") categoryId: String
    ) = repository.deleteById(categoryId)

    @PutMapping("/game/{game-id}/itemCategory")
    fun update(
            @PathVariable("game-id") gameId: String,
            @RequestBody itemCategoryForm: ItemCategoryForm
    ): ItemCategoryDto = itemCategoryForm
            .let { converter.toDomain(itemCategoryForm, gameId).apply { id = itemCategoryForm.id!! } }
            .let { repository.save(it) }
            .let { converter.toDto(it) }
}