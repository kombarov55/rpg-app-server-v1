package ru.novemis.rpgapp.controller.view

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.controller.UserAccountController
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.dto.view.OfficeViewDto
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil

@RestController
@RequestMapping("/officeView")
class OfficeViewController(
    private val jwtUtil: JWTUtil,
    private val userAccountRepository: UserAccountRepository,
    private val userAccountConverter: UserAccountConverter
) {

    @GetMapping
    fun getPageDto(@RequestHeader("Authorization") jwtToken: String): OfficeViewDto = OfficeViewDto(
        userAccount = userAccountRepository.findByUserId(jwtUtil.getUserIdFromRawToken(jwtToken))!!
            .let { userAccountConverter.toShortDto(it) }
    )
}