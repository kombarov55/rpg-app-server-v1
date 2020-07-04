package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.MerchandiseCategoryConverter
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseCategoryDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseCategoryForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseCategoryRepository

@RestController
class MerchandiseCategoryController(
        private val merchandiseCategoryRepository: MerchandiseCategoryRepository,
        private val merchandiseCategoryConverter: MerchandiseCategoryConverter
) {

    @PostMapping("/shop/{shop-id}/merchandiseCategory")
    fun save(
            @PathVariable("shop-id") shopId: String,
            @RequestBody merchandiseCategoryForm: MerchandiseCategoryForm
    ): MerchandiseCategoryDto = merchandiseCategoryForm
            .let { merchandiseCategoryConverter.toDomain(merchandiseCategoryForm, shopId) }
            .let { merchandiseCategoryRepository.save(it) }
            .let { merchandiseCategoryConverter.toDto(it) }
}