package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.converter.SpellPurchaseOptionConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SpellPurchaseOptionDto
import ru.novemis.rpgapp.dto.game.skill.form.SpellPurchaseOptionForm
import ru.novemis.rpgapp.repository.game.skillcategory.SchoolLvlRepository
import ru.novemis.rpgapp.repository.game.skillcategory.SpellPurchaseOptionRepository
import java.lang.RuntimeException
import javax.transaction.Transactional
import kotlin.math.max

@RestController
open class SpellPurchaseOptionController(
        private val converter: SpellPurchaseOptionConverter,
        private val repository: SpellPurchaseOptionRepository,

        private val schoolLvlRepository: SchoolLvlRepository
) {

    @PostMapping("/schoolLvl/{id}/spellPurchaseOption")
    @Transactional
    open fun addSpellPurchaseOptionToSchoolLvl(
            @PathVariable("id") schoolLvlId: String,
            @RequestBody form: SpellPurchaseOptionForm
    ): SpellPurchaseOptionDto {
        val schoolLvl = schoolLvlRepository.findById(schoolLvlId).get()

        return converter.toDomain(form, schoolLvl.spellSchool!!.skillCategory!!.game!!.id).apply {
            this.schoolLvl = schoolLvl
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }

    @PutMapping("/spellPurchaseOption/{id}")
    @Transactional
    open fun editSpellPurchaseOption(
            @PathVariable("id") id: String,
            @RequestBody form: SpellPurchaseOptionForm
    ): SpellPurchaseOptionDto {
        val savedEntity = repository.findById(id).get()
        val convertedInstance = converter.toDomain(form, savedEntity.schoolLvl!!.spellSchool!!.skillCategory!!.game!!.id)


        return savedEntity.apply {
            priceCombinations = convertedInstance.priceCombinations
        }.let { repository.save(it) }.let { converter.toDto(it) }
    }

    @DeleteMapping("spellPurchaseOption/{id}")
    @Transactional
    open fun delete(
            @PathVariable("id") id: String
    ): SpellPurchaseOptionDto {
        val spellPurchaseOption = repository.findById(id).get()
        val schoolLvl = spellPurchaseOption.schoolLvl!!
        val maxSpellCount = schoolLvl.spellPurchaseOptions.map { it.spellCount }.max()

        if (spellPurchaseOption.spellCount != maxSpellCount) {
            throw RuntimeException("I can delete only last element!")
        }

        return converter.toDto(spellPurchaseOption).also {
            schoolLvl.spellPurchaseOptions = schoolLvl.spellPurchaseOptions.filter { it.id != id }
            schoolLvlRepository.save(schoolLvl)
            repository.deleteById(id)
        }
    }

}