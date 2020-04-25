package ru.novemis.rpgapp.http

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


@Component
class HttpConnector {

    fun get(url: String): String {
        return makeRequest("GET", url, DEFAULT_HEADERS, "")
    }

    fun post(url: String, body: String?): String {
        return makeRequest("POST", url, DEFAULT_HEADERS, body)
    }

    private fun makeRequest(method: String, url: String, headers: Map<String, String>, body: String?): String {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = method
        headers.forEach { (k: String?, v: String?) -> connection.setRequestProperty(k, v) }
        logRequest(method, url, headers, body)
        connection.connect()
        if (body != null && !body.isEmpty()) {
            val bytes = body.toByteArray(charset("UTF-8"))
            connection.outputStream.write(bytes)
            connection.outputStream.flush()
        }
        val responseCode = "" + connection.responseCode
        val `in` = if (responseCode.startsWith("5") || responseCode.startsWith("4")) connection.errorStream else connection.inputStream
        val rs = readAll(`in`)
        logResponse(method, url, connection.headerFields, rs)
        return rs
    }

    private fun logRequest(method: String, url: String, headers: Map<String, String>, body: String?) {
        var str = "\r\n"
        str += "$method $url\r\n"
        if (!headers.isEmpty()) {
            str += "Headers: \r\n"
            for ((k, v) in headers) {
                str += "  $k -> $v\r\n"
            }
        }
        if (body != null && !body.isEmpty()) {
            str += body + "\r\n"
        }
        HttpConnector.log.debug(str)
    }

    private fun logResponse(method: String, url: String, headers: Map<String, List<String>>, body: String?) {
        var str = "\r\n"
        str += "Response for $method $url\r\n"
        if (!headers.isEmpty()) {
            str += "Headers: \r\n"
            if (!headers.isEmpty()) {
                for ((k, value) in headers) {
                    val v = java.lang.String.join(", ", value)
                    str += "  $k -> $v\r\n"
                }
            }
        }
        if (body != null && !body.isEmpty()) {
            str += body + "\r\n"
        }
        HttpConnector.log.info(str)
    }

    private fun readAll(inputStream: InputStream?): String {
        if (inputStream == null) {
            return ""
        }
        Scanner(inputStream, "UTF-8").use { scanner ->
            return if (scanner.hasNext()) {
                scanner.useDelimiter("\\A").next()
            } else {
                ""
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(HttpConnector.javaClass)

        private val DEFAULT_HEADERS: Map<String, String> = mapOf(

        )
    }
}
