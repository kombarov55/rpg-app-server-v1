package ru.novemis.rpgapp.service

import org.springframework.stereotype.Service
import ru.novemis.rpgapp.converter.SkillCategoryConverter
import ru.novemis.rpgapp.dto.game.skill.form.SkillCategoryForm
import ru.novemis.rpgapp.repository.game.skillcategory.SkillCategoryRepository

@Service
class SkillCategoryService(
        private val skillCategoryRepository: SkillCategoryRepository,
        private val skillCategoryConverter: SkillCategoryConverter
) {

    fun save(skillCategoryForm: SkillCategoryForm, gameId: String): Unit {
        skillCategoryForm
                .let { skillCategoryConverter.toDomain(it, gameId) }
                .let { skillCategoryRepository.save(it) }
//                .let { skillCategoryConverter.toDto(it) }
    }

}