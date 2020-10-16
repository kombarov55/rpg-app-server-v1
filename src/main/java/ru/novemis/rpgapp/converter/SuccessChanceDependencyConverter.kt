package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.domain.game.crafting.SuccessChanceDependency
import ru.novemis.rpgapp.dto.game.crafting.dto.SuccessChanceDependencyDto
import ru.novemis.rpgapp.dto.game.crafting.form.SuccessChanceDependencyForm

@Component
class SuccessChanceDependencyConverter {

    fun toDomain(form: SuccessChanceDependencyForm, recipe: Recipe? = null): SuccessChanceDependency {
        return SuccessChanceDependency(
                min = form.min,
                max = form.max,
                percent = form.percent,
                recipe = recipe
        )
    }

    fun toDto(domain: SuccessChanceDependency): SuccessChanceDependencyDto {
        return SuccessChanceDependencyDto(
                id = domain.id,
                min = domain.min,
                max = domain.max,
                percent = domain.percent
        )

    }

}