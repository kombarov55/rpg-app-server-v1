package ru.novemis.rpgapp.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.FileReader
import java.util.*
import javax.annotation.PostConstruct


@Component
class HiddenProperties(
        @Value("classpath:hidden.properties")
        private val hiddenPropsResource: Resource
) {

    private val internal = Properties()

    @PostConstruct
    fun load() {
        internal.load(FileReader(hiddenPropsResource.file))
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
