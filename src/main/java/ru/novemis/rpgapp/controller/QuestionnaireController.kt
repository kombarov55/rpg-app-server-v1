package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm
import ru.novemis.rpgapp.service.QuestionnaireService

@RestController
@RequestMapping("/questionnaire")
class QuestionnaireController(
        private val questionnaireService: QuestionnaireService
) {

    @PostMapping
    fun save(@RequestBody form: QuestionnaireForm) {
        questionnaireService.save(form)
    }

}