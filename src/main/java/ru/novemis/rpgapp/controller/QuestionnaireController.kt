package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireShortDto
import ru.novemis.rpgapp.service.QuestionnaireService

@RestController
@RequestMapping("/questionnaire")
class QuestionnaireController(
        private val questionnaireService: QuestionnaireService
) {

    @PostMapping
    fun save(@RequestBody form: QuestionnaireForm): QuestionnaireShortDto {
        return questionnaireService.save(form)
    }

    @GetMapping("/game/{game-id}/questionnaire")
    fun findShortByGameId(@PathVariable("game-id") gameId: String): List<QuestionnaireShortDto> {
        return questionnaireService.findShortByGameId(gameId)
    }

}