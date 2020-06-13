package ru.novemis.rpgapp.util

fun appendProtocol(url: String): String =
        if (!url.startsWith("http"))
            "https://" + url
        else url