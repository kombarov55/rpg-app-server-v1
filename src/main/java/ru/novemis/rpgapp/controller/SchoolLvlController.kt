package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SchoolLvlConverter
import ru.novemis.rpgapp.domain.game.skill.SchoolLvl
import ru.novemis.rpgapp.dto.game.skill.dto.SchoolLvlDto
import ru.novemis.rpgapp.repository.game.skillcategory.SchoolLvlRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellSchoolRepository
import java.lang.RuntimeException
import javax.transaction.Transactional

@RestController
open class SchoolLvlController(
        private val converter: SchoolLvlConverter,
        private val repository: SchoolLvlRepository,

        private val spellSchoolRepository: SpellSchoolRepository
) {

    @GetMapping("/schoolLvl/{id}")
    @Transactional
    open fun getById(
            @PathVariable("id") id: String
    ): SchoolLvlDto = repository.findById(id).get().let { converter.toDto(it) }

    @PostMapping("/spellSchool/{id}/schoolLvl")
    @Transactional
    open fun addSchoolLvlToSpellSchool(
            @PathVariable("id") id: String
    ): SchoolLvlDto {
        val spellSchool = spellSchoolRepository.findById(id).get()

        val maxExistingLvl = spellSchool.schoolLvls.map { it.lvl }.max() ?: 0

        return SchoolLvl(
                lvl = maxExistingLvl + 1,
                spellSchool =  spellSchool
        ).let { repository.save(it) }.let { converter.toDto(it) }
    }

    @DeleteMapping("/schoolLvl/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): SchoolLvlDto {
        val schoolLvl = repository.findById(id).get()

        if (schoolLvl.spells.isNotEmpty() || schoolLvl.spellPurchaseOptions.isNotEmpty()) {
            throw RuntimeException("Cannot delete not empty school lvl")
        }

        return converter.toDto(schoolLvl).also { repository.delete(schoolLvl) }
    }

}