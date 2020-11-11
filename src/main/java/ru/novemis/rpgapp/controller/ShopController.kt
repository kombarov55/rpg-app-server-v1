package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.ShopConverter
import ru.novemis.rpgapp.domain.game.shop.ShopType
import ru.novemis.rpgapp.dto.game.shop.dto.ShopDto
import ru.novemis.rpgapp.dto.game.shop.form.ShopForm
import ru.novemis.rpgapp.repository.game.shop.ShopRepository
import javax.transaction.Transactional

@RestController
open class ShopController(
        private val repository: ShopRepository,
        private val converter: ShopConverter
) {

    @GetMapping("/shop/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): ShopDto {
        return repository.findById(id).get().let { converter.toDto(it) }
    }

    data class ShopPatchForm(
            val type: ShopType? = null
    )

    @PatchMapping("/shop/{id}")
    open fun patch(
            @PathVariable("id") id: String,
            @RequestBody form: ShopPatchForm
    ): ShopDto {
        return repository.findById(id).get()
                .applyPatch(form)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/shop/{id}")
    open fun update(
            @PathVariable("id") id: String,
            @RequestBody form: ShopForm
    ): ShopDto {
        return converter.toDomain(form)
                .apply { this.id = id }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

}