package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.novemis.rpgapp.dto.login.LoginDto
import ru.novemis.rpgapp.dto.login.LoginRsDto
import ru.novemis.rpgapp.service.UserAccountService
import ru.novemis.rpgapp.util.JWTUtil

@RestController
class CredentialsController(
        private val userAccountService: UserAccountService,
        private val jwtUtil: JWTUtil
) {

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDto): LoginRsDto {
        val account = userAccountService.getAccountByUserId(loginDto.login.toInt())
        val token = jwtUtil.generateToken(account)
        return LoginRsDto(token)
    }

}