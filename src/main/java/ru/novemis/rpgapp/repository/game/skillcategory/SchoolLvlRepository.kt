package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.SchoolLvl

interface SchoolLvlRepository : CrudRepository<SchoolLvl, String> {
}