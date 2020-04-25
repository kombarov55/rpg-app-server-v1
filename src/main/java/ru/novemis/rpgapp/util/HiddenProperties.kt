package ru.novemis.rpgapp.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.FileReader
import java.util.*
import javax.annotation.PostConstruct


@Component
class HiddenProperties(
        @Value("\${hidden-props-path}")
        private val hiddenPropsPath: String
) {

    private val internal = Properties()

    @PostConstruct
    fun load() {
        internal.load(FileReader(hiddenPropsPath))
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
