package ru.novemis.rpgapp.util

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct


@Component
class HiddenProperties{

    private val internal = Properties()

    @PostConstruct
    fun load() {
        internal.load(ClassPathResource("hidden.properties").inputStream)
    }

    val appId: Int
        get() = internal.getProperty("vk.app-id").toInt()

    val clientSecret: String
        get() = internal.getProperty("vk.client-secret")

    val serviceToken: String
        get() = internal.getProperty("vk.service-token")

    val groupSecret: String
        get() = internal.getProperty("vk.group-secret")
}
