package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.QuestionnaireTemplateConverter
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.dto.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.dto.game.questionnaire_template.form.QuestionnaireTemplateForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.questionnaire_template.QuestionnaireTemplateRepository
import javax.transaction.Transactional

@RestController
open class QuestionnaireTemplateController(
        private val repository: QuestionnaireTemplateRepository,
        private val converter: QuestionnaireTemplateConverter,

        private val gameRepository: GameRepository
) {

    @GetMapping("/game/{game-id}/questionnaireTemplate")
    @Transactional
    open fun findAllByGameId(
            @PathVariable("game-id") gameId: String
    ): List<QuestionnaireTemplateShortDto> {
        return repository.findAllByGameId(gameId).map { converter.toShortDto(it) }
    }

    @GetMapping("/questionnaireTemplate/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): QuestionnaireTemplateDto {
        return repository.findById(id).get().let { converter.toDto(it) }
    }

    @PostMapping("/game/{game-id}/questionnaireTemplate")
    @Transactional
    open fun save(
            @PathVariable("game-id") gameId: String,
            @RequestBody form: QuestionnaireTemplateForm
    ): QuestionnaireTemplateShortDto {
        return converter.toDomain(form, gameRepository.findById(gameId).get())
                .let { repository.save(it) }
                .let { converter.toShortDto(it) }
    }

    @PutMapping("questionnaireTemplate/{id}")
    @Transactional
    open fun edit(
            @PathVariable("id") id: String,
            @RequestBody form: QuestionnaireTemplateForm
    ): QuestionnaireTemplateShortDto {
        return repository.findById(id).get().apply {
            name = form.name
            img = form.img
            description = form.description
        }.let { repository.save(it) }.let { converter.toShortDto(it) }
    }

    @DeleteMapping("questionnaireTemplate/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): QuestionnaireTemplateShortDto {
        return repository.findById(id).get().also {
            repository.deleteById(id)
        }.let { converter.toShortDto(it) }
    }

}