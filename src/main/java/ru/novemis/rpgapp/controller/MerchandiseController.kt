
package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.MerchandiseConverter
import ru.novemis.rpgapp.dto.game.shop.dto.MerchandiseDto
import ru.novemis.rpgapp.dto.game.shop.form.MerchandiseForm
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import javax.transaction.Transactional

@RestController
open class MerchandiseController(
        private val repository: MerchandiseRepository,
        private val converter: MerchandiseConverter
) {

    @GetMapping("/game/{game-id}/merchandise")
    @Transactional
    open fun getAll(
            @PathVariable("game-id") gameId: String
    ): List<MerchandiseDto> {
        return repository.findAllByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/merchandise")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: MerchandiseForm
    ):MerchandiseDto = form
            .let { converter.toDomain(form, gameId) }
            .let { repository.save(it) }
            .let { converter.toDto(it) }

    @PutMapping("/game/{game-id}/merchandise/{id}")
    @Transactional
    open fun update(
            @PathVariable("game-id") gameId: String,
            @PathVariable("id") id: String,
            @RequestBody form: MerchandiseForm
    ) = form
            .let { converter.toDomain(form, gameId) }
            .also { it.id = id }
            .let { repository.save(it) }

    @DeleteMapping("/game/{game-id}/merchandise/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ) = repository.deleteById(id)

}