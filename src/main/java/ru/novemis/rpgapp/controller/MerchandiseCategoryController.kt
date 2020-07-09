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

    @PostMapping("/shop/{shop-id}/merchandiseCategory")
    fun save(
            @PathVariable("shop-id") shopId: String,
            @RequestBody merchandiseCategoryForm: MerchandiseCategoryForm
    ): MerchandiseCategoryDto = merchandiseCategoryForm
            .let { converter.toDomain(merchandiseCategoryForm, shopId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/shop/{shop-id}/merchandiseCategory/{category-id}")
    fun delete(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("category-id") categoryId: String
    ) = repository.deleteById(categoryId)

    @PutMapping("/shop/{shop-id}/merchandiseCategory")
    fun update(
            @PathVariable("shop-id") shopId: String,
            @RequestBody merchandiseCategoryForm: MerchandiseCategoryForm
    ): MerchandiseCategoryDto = merchandiseCategoryForm
            .let { converter.toDomain(merchandiseCategoryForm, shopId).apply { id = merchandiseCategoryForm.id } }
            .let { repository.save(it) }
            .let { converter.toDto(it) }
}