package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.converter.SkillCategoryConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@Service
open class SkillCategoryService(
        private val skillCategoryRepository: SkillCategoryRepository,
        private val skillCategoryConverter: SkillCategoryConverter
) {

    fun save(skillCategoryForm: SkillCategoryForm, gameId: String): SkillCategoryDto =
        skillCategoryForm
                .let { skillCategoryConverter.toDomain(it, gameId) }
                .let { skillCategoryRepository.save(it) }
                .let { skillCategoryConverter.toDto(it) }

    @Transactional
    open fun findById(id: String): SkillCategoryDto =
            id
                    .let { skillCategoryRepository.findById(it) }
                    .map { skillCategoryConverter.toDto(it) }
                    .orElseThrow { IllegalArgumentException("skillcategory $id not found") }

}