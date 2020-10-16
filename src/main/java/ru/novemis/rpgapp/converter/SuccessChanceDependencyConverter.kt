package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.crafting.SuccessChanceDependency
import ru.novemis.rpgapp.dto.game.crafting.dto.SuccessChanceDependencyDto
import ru.novemis.rpgapp.dto.game.crafting.form.SuccessChanceDependencyForm

@Component
class SuccessChanceDependencyConverter {

    fun toDomain(form: SuccessChanceDependencyForm): SuccessChanceDependency {
        return SuccessChanceDependency(
                min = form.min,
                max = form.max,
                percent = form.percent
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