package ru.novemis.rpgapp.repository.game.skillcategory

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.skill.SpellPurchaseOption

interface SpellPurchaseOptionRepository : CrudRepository<SpellPurchaseOption, String> {
}