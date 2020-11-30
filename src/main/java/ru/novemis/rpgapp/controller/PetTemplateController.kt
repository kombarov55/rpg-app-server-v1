package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.PetTemplateConverter
import ru.novemis.rpgapp.dto.game.pet.dto.PetTemplateDto
import ru.novemis.rpgapp.dto.game.pet.form.PetTemplateForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.pet.PetTemplateRepository
import javax.transaction.Transactional

@RestController
open class PetTemplateController(
        private val repository: PetTemplateRepository,
        private val converter: PetTemplateConverter,
        private val gameRepository: GameRepository
) {

    @GetMapping("/game/{game-id}/pet-template")
    @Transactional
    open fun findByGameId(
            @PathVariable("game-id") gameId: String
    ): List<PetTemplateDto> {
        return repository.findByGameId(gameId).map { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/pet-template")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: PetTemplateForm
    ): PetTemplateDto {
        val game = gameRepository.findById(gameId).get()

        return converter.toDomain(form, game)
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @PutMapping("/pet-template/{id}")
    @Transactional
    open fun edit(
            @PathVariable("id") id: String,
            @RequestBody form: PetTemplateForm
    ): PetTemplateDto {
        return repository.findById(id).get()
                .also { repository.delete(it) }
                .let { converter.toDomain(form, it.game).apply { this.id = id } }
                .let { repository.save(it) }
                .let { converter.toDto(it) }
    }

    @DeleteMapping("/pet-template/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): PetTemplateDto {
        return repository.findById(id).get()
                .also { repository.delete(it) }
                .let { converter.toDto(it) }
    }
}