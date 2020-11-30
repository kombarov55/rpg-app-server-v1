package ru.novemis.rpgapp.repository.game.pet

import org.springframework.data.repository.CrudRepository
import ru.novemis.rpgapp.domain.game.pet.PetUpgrade

interface PetUpgradeRepository : CrudRepository<PetUpgrade, String> {
}