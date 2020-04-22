package ru.novemis.rpgapp.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dao.UploadCacheRepository
import java.io.File

@RestController
@RequestMapping("/upload")
class FileUploadController(
        @Value("\${upload-dir}")
        private val uploadDir: String,

        private val uploadCacheRepository: UploadCacheRepository
) {

    @PostMapping(value = ["/{uploadUid}"], consumes = ["*/*;charset=UTF-8"])
    fun upload(@RequestBody body: String, @PathVariable("uploadUid") uploadUid: String) {
        val split = body.split("\r\n\r\n")
        val head = split[0]
        val content = split[1]

        val imageExtension = head.split("\r\n")[1].split(";")[2].split(".")[1].replace("\"", "")

        // посчитать сколько уже есть ссылок и добавить к этому единицу.
        val links = uploadCacheRepository.findById(uploadUid)
        val nextCount = links.size + 1
        val fileName = "${uploadDir}/${uploadUid}__${nextCount}.$imageExtension"
        uploadCacheRepository.addLink(uploadUid, fileName)

        File(fileName).writeBytes(content.toByteArray())

    }

}