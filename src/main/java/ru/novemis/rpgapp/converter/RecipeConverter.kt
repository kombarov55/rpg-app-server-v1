package ru.novemis.rpgapp.converter

import org.mapstruct.Mapper
import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.dto.game.crafting.dto.RecipeDto

@Mapper
interface RecipeConverter {

    fun toDto(domain: Recipe): RecipeDto

}