package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.PriceCombinationConverter
import ru.novemis.rpgapp.converter.SpellConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellForm
import ru.novemis.rpgapp.repository.game.character.GameCharacterRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SchoolLvlRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellRepository
import ru.novemis.rpgapp.service.SkillCategoryService
import ru.novemis.rpgapp.service.SpellService
import javax.transaction.Transactional

@RestController
open class SpellController(
        private val repository: SpellRepository,
        private val converter: SpellConverter,
        private val service: SpellService,
        private val characterRepository: GameCharacterRepository,
        private val schoolLvlRepository: SchoolLvlRepository,
        private val skillCategoryRepository: SkillCategoryRepository,
        private val priceCombinationConverter: PriceCombinationConverter,
        private val skillCategoryService: SkillCategoryService
) {

    @PostMapping("/schoolLvl/{school-lvl-id}/spell")
    @Transactional
    open fun addSpellToSchoolLvl(
            @PathVariable("school-lvl-id") schoolLvlId: String,
            @RequestBody form: SpellForm

    ): SpellDto {
        val schoolLvl = schoolLvlRepository.findById(schoolLvlId).get()
        val spell = converter.toDomain(form).apply { this.schoolLvl = schoolLvl }

        return repository.save(spell).let { converter.toDto(it) }
    }

    @PutMapping("/spell/{id}")
    @Transactional
    open fun updateSpell(
            @PathVariable("id") id: String,
            @RequestBody form: SpellForm
    ): SpellDto {
        val savedEntity = repository.findById(id).get()

        return converter.toDomain(form).apply {
            this.id = savedEntity.id
            schoolLvl = savedEntity.schoolLvl
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }

    @DeleteMapping("/spell/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): SpellDto {
        return repository.findById(id)
                .get().also { repository.delete(it) }
                .let { converter.toDto(it) }
    }

    @GetMapping("/game/{game-id}/spell/available")
    @Transactional
    open fun findAvailableSpells(
            @PathVariable("game-id") gameId: String,
            @RequestParam("characterId") characterId: String
    ): List<SpellDto> {
        val character = characterRepository.findById(characterId).get()

        return service.findAvailableSpells(gameId, character).map { spell ->
            converter.toDto(spell).apply {
                prices = skillCategoryService.getPricesForSpell(spell, character.learnedSpells).map { priceCombinationConverter.toDto(it) }
            }
        }
    }
}