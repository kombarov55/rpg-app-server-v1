package ru.novemis.rpgapp.controller.procedures

import org.json.JSONObject
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.repository.game.RecipeRepository
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import javax.transaction.Transactional
import kotlin.random.Random

@RestController
@RequestMapping("/craft")
open class CraftProceduresController(
        private val recipeRepository: RecipeRepository,
        private val gameCharacterRepository: GameCharacterRepository
) {

    data class CraftingRq(
            val characterId: String = "",
            val recipeId: String = ""
    )

    @PostMapping("/performCrafting.do")
    @Transactional
    open fun performCrafting(@RequestBody rq: CraftingRq): JSONObject {
        val recipe = recipeRepository.findById(rq.recipeId).get()
        val character = gameCharacterRepository.findById(rq.characterId).get()

        val haveEnoughSkill = character.learnedSkills
                .find { skillToLvl -> recipe.dependantSkill!!.id == skillToLvl.skill!!.id }
                ?.amount ?: 0 >= recipe.minSkillLvl

        if (!haveEnoughSkill) {
            throw RuntimeException("Недостаточно навыка для крафта")
        }

        val haveEnoughItems = recipe.ingredients.all { itemTemplateAmount ->
            character.items.filter { it.itemTemplate!!.id == itemTemplateAmount.itemTemplate!!.id }
                    .size == itemTemplateAmount.amount
        }

        if (!haveEnoughItems) {
            throw RuntimeException("Выбрано недостаточно предметов для крафта")
        }

        val skillLvl = character.learnedSkills.find { it.skill!!.id == recipe.dependantSkill!!.id }!!.amount
        val successChanceDependency = recipe.successChanceDependencies.find { it.min < skillLvl && it.max ?: Integer.MAX_VALUE > skillLvl }!!

        val isSuccess = Random.nextInt(100) < successChanceDependency.percent

        recipe.ingredients.forEach { ingredient ->
            repeat(ingredient.amount) {
                character.removeItem(ingredient.itemTemplate!!)
            }
        }

        gameCharacterRepository.save(character)

        if (isSuccess) {
            character.addItem(recipe.target!!)
            return JSONObject().put("success", true)
        } else {
            return JSONObject().put("success", false)
        }


    }

}