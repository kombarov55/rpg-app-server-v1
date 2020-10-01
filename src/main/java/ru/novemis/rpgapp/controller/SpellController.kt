package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SpellConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SpellDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellForm
import ru.novemis.rpgapp.repository.game.skillcategory.SchoolLvlRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellRepository
import javax.transaction.Transactional

@RestController
open class SpellController(
        private val repository: SpellRepository,
        private val converter: SpellConverter,

        private val schoolLvlRepository: SchoolLvlRepository
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
            schoolLvl = savedEntity.schoolLvl
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }
}