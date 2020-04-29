package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.login.LoginRqDto
import ru.novemis.rpgapp.dto.login.LoginRsDto
import ru.novemis.rpgapp.service.UserAccountService
import ru.novemis.rpgapp.util.JWTUtil

@RestController
class CredentialsController(
        private val userAccountService: UserAccountService,
        private val jwtUtil: JWTUtil
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRqDto: LoginRqDto): LoginRsDto {
        val account = userAccountService.getAccountByUserId(loginRqDto.login)
        val token = jwtUtil.generateToken(account)
        return LoginRsDto(token)
    }

}