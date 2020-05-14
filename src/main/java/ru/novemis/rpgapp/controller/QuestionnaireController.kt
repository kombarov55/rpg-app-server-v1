package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.questionnaire.QuestionnaireForm

@RestController
@RequestMapping("/questionnaire")
class QuestionnaireController {

    @PostMapping
    fun save(@RequestBody form: QuestionnaireForm) {
        println("it parsed")
    }

}