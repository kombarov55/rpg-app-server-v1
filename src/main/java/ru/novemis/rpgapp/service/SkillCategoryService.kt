package ru.novemis.rpgapp.service

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import ru.novemis.rpgapp.converter.SkillCategoryConverter
import ru.novemis.rpgapp.dto.game.skill.dto.SkillCategoryDto
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository
import java.lang.IllegalArgumentException
import javax.transaction.Transactional

@Service
open class SkillCategoryService(
        private val repository: SkillCategoryRepository,
        private val converter: SkillCategoryConverter
) {

    fun save(skillCategoryForm: SkillCategoryForm, gameId: String): SkillCategoryDto =
            skillCategoryForm
                    .let { converter.toDomain(it, gameId) }
                    .let { repository.save(it) }
                    .let { converter.toDto(it) }

    @Transactional
    open fun findById(id: String): SkillCategoryDto =
            id
                    .let { repository.findById(it) }
                    .map { converter.toDto(it) }
                    .orElseThrow { IllegalArgumentException("skillcategory $id not found") }

    @Transactional
    open fun update(id: String, body: SkillCategoryForm): SkillCategoryDto =
            repository.findById(id)
                    .orElseThrow { IllegalArgumentException("skillCategory $id not found") }
                    .apply {
                        name = body.name
                        img = body.img
                        description = body.description
                    }
                    .let { repository.save(it) }
                    .let { converter.toDto(it) }₽

}