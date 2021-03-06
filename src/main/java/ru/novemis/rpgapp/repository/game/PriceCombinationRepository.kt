package ru.novemis.rpgapp.repository.game

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.common.PriceCombination

interface PriceCombinationRepository : CrudRepository<PriceCombination, String> {
}