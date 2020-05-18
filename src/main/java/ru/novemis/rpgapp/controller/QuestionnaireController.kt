package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireShortDto
import ru.novemis.rpgapp.service.QuestionnaireService

@RestController
class QuestionnaireController(
        private val questionnaireService: QuestionnaireService
) {

    @PostMapping("/questionnaire")
    fun save(@RequestBody form: QuestionnaireForm): QuestionnaireShortDto {
        return questionnaireService.save(form)
    }

    @GetMapping("/game/{game-id}/questionnaire")
    fun findShortByGameId(@PathVariable("game-id") gameId: String): List<QuestionnaireShortDto> {
        return questionnaireService.findShortByGameId(gameId)
    }

    @PutMapping("/questionnaire/{questionnaire-id}")
    fun update(@PathVariable("questionnaire-id") id: String,
               @RequestBody form: QuestionnaireForm): QuestionnaireShortDto {
        return questionnaireService.update(id, form)
    }

    @GetMapping("/questionnaire/{questionnaire-id}")
    fun getById(@PathVariable("questionnaire-id") id: String): QuestionnaireForm {
        return questionnaireService.getById(id)
    }
    @DeleteMapping("/questionnaire/{questionnaire-id}")
    fun delete(@PathVariable("questionnaire-id") id: String): QuestionnaireShortDto {
        return questionnaireService.delete(id)
    }

}