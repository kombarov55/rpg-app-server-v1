package ru.novemis.rpgapp.converter

import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.game.crafting.ItemTemplateAmount
import ru.novemis.rpgapp.domain.game.crafting.Recipe
import ru.novemis.rpgapp.domain.game.shop.ItemTemplate
import ru.novemis.rpgapp.dto.game.crafting.dto.RecipeDto
import ru.novemis.rpgapp.dto.game.crafting.form.RecipeForm
import ru.novemis.rpgapp.repository.game.GameRepository
import ru.novemis.rpgapp.repository.game.shop.ItemTemplateRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillRepository

@Component
class RecipeConverter(
        private val itemTemplateConverter: ItemTemplateConverter,
        private val skillConverter: SkillConverter,
        private val successChanceDependencyConverter: SuccessChanceDependencyConverter,
        private val itemTemplateRepository: ItemTemplateRepository,
        private val skillRepository: SkillRepository,
        private val gameRepository: GameRepository,
        private val itemTemplateAmountConverter: ItemTemplateAmountConverter
) {

    fun toDomain(form: RecipeForm, gameId: String? = null): Recipe {
        return Recipe().apply {
            val recipe = this

            target = itemTemplateRepository.findById(form.target!!.id!!).get()
            ingredients = form.ingredients.map { itemTemplateAmountConverter.toDomain(it, recipe) }
            dependantSkill = skillRepository.findById(form.dependantSkill!!.id).get()
            minSkillLvl = form.minSkillLvl
            successChanceDependencies = form.successChanceDependencies.map { successChanceDependencyConverter.toDomain(it).apply { this.recipe = recipe } }
            game = gameId?.let { gameRepository.findById(it).orElse(null) }
        }
    }

    fun toDto(domain: Recipe): RecipeDto {
        return RecipeDto(
                id = domain.id,
                target = itemTemplateConverter.toDto(domain.target!!),
                ingredients = domain.ingredients.map { itemTemplateAmountConverter.toDto(it) },
                dependantSkill = skillConverter.toShortDto(domain.dependantSkill!!),
                minSkillLvl = domain.minSkillLvl,
                successChanceDependencies = domain.successChanceDependencies.map { successChanceDependencyConverter.toDto(it) }
        )
    }
}