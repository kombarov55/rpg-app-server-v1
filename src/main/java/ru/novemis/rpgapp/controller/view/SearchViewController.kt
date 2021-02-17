package ru.novemis.rpgapp.controller.view

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.dto.useraccount.dto.UserAccountDto
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import javax.transaction.Transactional

@RestController
class SearchViewController(
    private val repository: UserAccountRepository,
    private val converter: UserAccountConverter
) {

    @GetMapping("/searchView/search")
    @Transactional
    fun search(
        @RequestParam("name") name: String
    ): List<UserAccountDto> {
        var result = repository.searchByNameOrCharacterName(name)
        if (result.isEmpty()) {
            result = repository.searchByCharacterName(name)
        }

        return result.map { converter.toDto(it) }
    }

}