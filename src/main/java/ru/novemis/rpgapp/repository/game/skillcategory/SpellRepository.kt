package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.Spell

interface SpellRepository : CrudRepository<Spell, String> {
}