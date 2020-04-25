package ru.novemis.rpgapp.http

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.novemis.rpgapp.util.HiddenProperties
import java.net.URLEncoder
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct


@Component
class VkRequests(
        @Value("\${vk.api-version}")
        private val version: String,

        private val hiddenProperties: HiddenProperties,
        private val httpConnector: HttpConnector
) {
    private var token: String? = null
    @PostConstruct
    fun init() {
        token = hiddenProperties!!.groupSecret
    }

    fun getUserInfo(userId: String): String {
        val url = buildUrl("users.get", mapOf(
                "user_ids" to userId,
                "fields" to "photo_50"
        ))

        return httpConnector.get(url)
    }

    fun sendMsg(peerId: Int, message: String?) {
        val url = buildUrl("messages.send", mapOf(
                "random_id" to "" + Random().nextInt(),
                "peer_id" to "" + peerId,
                "message" to message
        ))
        httpConnector.get(url)
    }

    fun conversations() {
        val url = buildUrl("messages.getConversations", emptyMap())
        httpConnector.get(url)
    }

    @Throws(Throwable::class)
    fun removeChatUser(chatId: Int, userId: Int) {
        val url = buildUrl("messages.removeChatUser", mapOf(
                "user_id" to "" + userId,
                "chat_id" to "" + chatId
        ))
        httpConnector.get(url)
    }

    private fun buildUrl(methodName: String, params: Map<String, String?>): String {
        val extendedParams = params + mapOf(
                "v" to version,
                "access_token" to token
        )
        val paramsJoined = extendedParams.entries.stream()
                .map { entry: Map.Entry<String, String?> -> URLEncoder.encode(entry.key) + "=" + URLEncoder.encode(entry.value) }
                .collect(Collectors.joining("&"))

        return "https://api.vk.com/method/$methodName?$paramsJoined"
    }
}
