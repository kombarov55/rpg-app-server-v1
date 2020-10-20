package ru.novemis.rpgapp.repository.game

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.common.Balance

interface BalanceRepository : CrudRepository<Balance, String> {
}