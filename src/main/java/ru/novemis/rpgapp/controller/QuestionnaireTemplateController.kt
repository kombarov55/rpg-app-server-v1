package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateForm
import ru.novemis.rpgapp.dto.game.questionnaire_template.QuestionnaireTemplateShortDto
import ru.novemis.rpgapp.service.QuestionnaireTemplateService

@RestController
class QuestionnaireTemplateController(
        private val questionnaireTemplateService: QuestionnaireTemplateService
) {

    @PostMapping("/questionnaire")
    fun save(@RequestBody form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.save(form)
    }

    @GetMapping("/game/{game-id}/questionnaire")
    fun findShortByGameId(@PathVariable("game-id") gameId: String): List<QuestionnaireTemplateShortDto> {
        return questionnaireTemplateService.findShortByGameId(gameId)
    }

    @PutMapping("/questionnaire/{questionnaire-id}")
    fun update(@PathVariable("questionnaire-id") id: String,
               @RequestBody form: QuestionnaireTemplateForm): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.update(id, form)
    }

    @GetMapping("/questionnaire/{questionnaire-id}")
    fun getById(@PathVariable("questionnaire-id") id: String): QuestionnaireTemplateForm {
        return questionnaireTemplateService.getById(id)
    }
    @DeleteMapping("/questionnaire/{questionnaire-id}")
    fun delete(@PathVariable("questionnaire-id") id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.delete(id)
    }

    @GetMapping("/questionnaire/{questionnaire-id}/restore")
    fun restore(@PathVariable("questionnaire-id") id: String): QuestionnaireTemplateShortDto {
        return questionnaireTemplateService.restore(id)
    }

}