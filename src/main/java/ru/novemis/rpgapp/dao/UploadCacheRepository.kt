package ru.novemis.rpgapp.dao

import org.springframework.stereotype.Service

@Service
open class UploadCacheRepository {

    private val storageMap = mutableMapOf<String, MutableList<String>>()

    fun save(uploadUid: String, links: MutableList<String>) {
        storageMap[uploadUid] = links
    }

    fun addLink(uploadUid: String, vararg links: String) {
        val newLinks = (storageMap[uploadUid] ?: mutableListOf())
        newLinks.addAll(links)

        storageMap[uploadUid] = newLinks
    }

    fun findById(uploadUid: String): List<String> {
        return storageMap[uploadUid] ?: emptyList()
    }

    fun deleteById(uploadUid: String) {
        storageMap.remove(uploadUid)
    }

}