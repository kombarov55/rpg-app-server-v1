
package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.MerchandiseConverter
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository

@RestController
class MerchandiseController(
        private val repository: MerchandiseRepository,
        private val converter: MerchandiseConverter
) {

    @PostMapping("/game/{game-id}/merchandise")
    fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: MerchandiseForm
    ) = form
            .let { converter.toDomain(form, gameId) }
            .let { repository.save(it) }

    @PutMapping("/game/{game-id}/merchandise/{id}")
    fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: MerchandiseForm
    ) = form
            .let { converter.toDomain(form, gameId) }
            .also { it.id = id }
            .let { repository.save(it) }

    @DeleteMapping("/game/{game-id}/merchandise/{id}")
    fun delete(
            @PathVariable("id") id: String
    ) = repository.deleteById(id)

}