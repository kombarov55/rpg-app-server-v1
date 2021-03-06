package ru.novemis.rpgapp.controller.view

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.converter.UserAccountConverter
import ru.novemis.rpgapp.dto.view.OfficeViewDto
import ru.novemis.rpgapp.repository.useraccount.UserAccountRepository
import ru.novemis.rpgapp.util.JWTUtil
import javax.transaction.Transactional

@RestController
@RequestMapping("/officeView")
class OfficeViewController(
    private val jwtUtil: JWTUtil,
    private val userAccountRepository: UserAccountRepository,
    private val userAccountConverter: UserAccountConverter
) {

    @GetMapping
    @Transactional
    fun getPageDto(@RequestHeader("Authorization") jwtToken: String): OfficeViewDto {
        val userId = jwtUtil.getUserIdFromRawToken(jwtToken)
        val userAccount = userAccountRepository.findByUserId(userId)!!

        return OfficeViewDto(
            userAccount = userAccountConverter.toDto(userAccount)
        )
    }
}