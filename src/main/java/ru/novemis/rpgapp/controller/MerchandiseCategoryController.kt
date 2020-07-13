package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.MerchandiseCategoryConverter
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseCategoryForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseCategoryRepository

@RestController
class MerchandiseCategoryController(
        private val repository: MerchandiseCategoryRepository,
        private val converter: MerchandiseCategoryConverter
) {

    @GetMapping("/game/{game-id}/merchandiseCategory")
    fun getByGameId(
            @PathVariable("game-id") gameId: String
    ): List<MerchandiseCategoryDto> = repository.findByGameId(gameId).map { converter.toDto(it) }

    @PostMapping("/game/{game-id}/merchandiseCategory")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody merchandiseCategoryForm: MerchandiseCategoryForm
    ): MerchandiseCategoryDto = merchandiseCategoryForm
            .let { converter.toDomain(merchandiseCategoryForm, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/game/{game-id}/merchandiseCategory/{category-id}")
    fun delete(
            @PathVariable("game-id") gameId: String,
            @PathVariable("category-id") categoryId: String
    ) = repository.deleteById(categoryId)

    @PutMapping("/game/{game-id}/merchandiseCategory")
    fun update(
            @PathVariable("game-id") gameId: String,
            @RequestBody merchandiseCategoryForm: MerchandiseCategoryForm
    ): MerchandiseCategoryDto = merchandiseCategoryForm
            .let { converter.toDomain(merchandiseCategoryForm, gameId).apply { id = merchandiseCategoryForm.id!! } }
            .let { repository.save(it) }
            .let { converter.toDto(it) }
}