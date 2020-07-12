package ru.novemis.rpgapp.controller

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import ru.novemis.rpgapp.converter.MerchandiseConverter
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository

@Component
class MerchandiseController(
        private val repository: MerchandiseRepository,
        private val converter: MerchandiseConverter
) {

    @PostMapping("/shop/{shop-id}/merchandise")
    fun save(
            @PathVariable("shop-id") shopId: String,
            @RequestBody form: MerchandiseForm
    ) = form
            .let { converter.toDomain(form) }
            .let { repository.save(it) }

    @PutMapping("/shop/{shop-id}/merchandise/{id}")
    fun update(
            @PathVariable("shop-id") shopId: String,
            @PathVariable("id") id: String,
            @RequestBody form: MerchandiseForm
    ) = form
            .let { converter.toDomain(form) }
            .also { it.id = id }
            .let { repository.save(it) }

    @PutMapping("/shop/{shop-id}/merchandise/{id}")
    fun delete(
            @PathVariable("id") id: String
    ) = repository.deleteById(id)

}