package ru.novemis.rpgapp.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.domain.useraccount.UserAccount
import ru.novemis.rpgapp.domain.useraccount.UserAccountRole
import java.io.Serializable
import java.util.*


@Component
class JWTUtil : Serializable {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString(secret!!.toByteArray())).parseClaimsJws(token).body
    }

    fun getUsernameFromToken(token: String): String {
        return getAllClaimsFromToken(token).subject
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getAllClaimsFromToken(token).expiration
    }

    fun getRolesFromToken(token: String): List<UserAccountRole> {
        val rolesJoined = getAllClaimsFromToken(token)["roles"] as String
        return rolesJoined.split(", ")
                .map { UserAccountRole.valueOf(it) }
                .toList()
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(userAccount: UserAccount): String {
        val claims: MutableMap<String, Any> = HashMap()
        claims["roles"] = userAccount.role.name
        return doGenerateToken(claims, userAccount.userId.toString())
    }

    private fun doGenerateToken(claims: Map<String, Any>, username: String): String {
        val expirationTimeLong = expirationTime
        val createdDate = Date()
        val expirationDate = Date(createdDate.time + expirationTimeLong * 1000)
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.toByteArray()))
                .compact()
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }

    companion object {
        val expirationTime = 31556926L
    }
}