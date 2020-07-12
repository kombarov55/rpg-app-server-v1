package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.MerchandiseTypeConverter
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseTypeDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseTypeForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseTypeRepository

@RestController
class MerchandiseTypeController(
        private val repository: MerchandiseTypeRepository,
        private val converter: MerchandiseTypeConverter
) {

    @PostMapping("/shop/{shop-id}/merchandiseType")
    fun save(
            @PathVariable("shop-id") shopId: String,
            @RequestBody form: MerchandiseTypeForm
    ): MerchandiseTypeDto = form
            .let { converter.toDomain(it, shopId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @PutMapping("/shop/{shop-id}/merchandiseType/{id}")
    fun update(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("id") id: String,
            @RequestBody form: MerchandiseTypeForm
    ): MerchandiseTypeDto = form
            .let { converter.toDomain(it, shopId) }
            .also { it.id = id }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @DeleteMapping("/shop/{shop-id}/merchandiseType/{id}")
    fun delete(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("id") id: String
    ) = repository.deleteById(id)
}