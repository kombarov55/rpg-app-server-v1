package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.dto.game.crafting.dto.RecipeDto
import ru.novemis.rpgapp.dto.game.crafting.form.RecipeForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.MerchandiseRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class RecipeConverter(
        private val merchandiseConverter: MerchandiseConverter,
        private val warehouseEntryConverter: WarehouseEntryConverter,
        private val skillConverter: SkillConverter,
        private val successChanceDependencyConverter: SuccessChanceDependencyConverter,

        private val merchandiseRepository: MerchandiseRepository,
        private val skillRepository: SkillRepository,
        private val gameRepository: GameRepository
) {

    fun toDomain(form: RecipeForm, gameId: String? = null): Recipe {
        return Recipe().apply {
            target = merchandiseRepository.findById(form.target!!.id!!).get()
            ingredients = form.ingredients.map { warehouseEntryConverter.toDomain(it) }
            dependantSkill = skillRepository.findById(form.dependantSkill!!.id).get()
            minSkillLvl = form.minSkillLvl
            successChanceDependencies = form.successChanceDependencies.map { successChanceDependencyConverter.toDomain(it) }
            game = gameId?.let { gameRepository.findById(it).orElse(null) }
        }
    }

    fun toDto(domain: Recipe): RecipeDto {
        return RecipeDto(
                id = domain.id,
                target = merchandiseConverter.toShortDto(domain.target!!),
                ingredients = domain.ingredients.map { warehouseEntryConverter.toDto(it) },
                dependantSkill = skillConverter.toShortDto(domain.dependantSkill!!),
                minSkillLvl = domain.minSkillLvl,
                successChanceDependencies = domain.successChanceDependencies.map { successChanceDependencyConverter.toDto(it) }
        )
    }

}