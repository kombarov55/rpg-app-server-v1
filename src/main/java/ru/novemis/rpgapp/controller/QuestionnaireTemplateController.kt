package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.service.QuestionnaireTemplateService

@RestController
class QuestionnaireTemplateController(
        private val questionnaireTemplateService: QuestionnaireTemplateService
) {

    @PostMapping("/questionnaireTemplate")
    fun save(@RequestBody form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.save(form)
    }

    @GetMapping("/game/{game-id}/questionnaireTemplate")
    fun findShortByGameId(@PathVariable("game-id") gameId: String): List<QuestionnaireTemplateShortDto> {
        return questionnaireTemplateService.findShortByGameId(gameId)
    }

    @PutMapping("/questionnaireTemplate/{id}")
    fun update(@PathVariable("id") id: String,
               @RequestBody form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.update(id, form)
    }

    @GetMapping("/questionnaireTemplate/{id}")
    fun getById(@PathVariable("id") id: String): QuestionnaireTemplateForm {
        return questionnaireTemplateService.getById(id)
    }
    @DeleteMapping("/questionnaireTemplate/{id}")
    fun delete(@PathVariable("id") id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.delete(id)
    }

    @GetMapping("/questionnaireTemplate/{id}/restore")
    fun restore(@PathVariable("id") id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.restore(id)
    }

}