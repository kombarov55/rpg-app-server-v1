package ru.novemis.rpgapp.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import ru.novemis.rpgapp.dto.common.UploadRsDto
import ru.novemis.rpgapp.repository.announcement.UploadCacheRepository


@RestController
@RequestMapping("/upload")
class FileUploadController(
        @Value("\${upload-dir}")
        private val uploadDir: String,

        private val uploadCacheRepository: UploadCacheRepository
) {

    @PostMapping(consumes = ["multipart/form-data"])
    fun upload(@RequestPart(value = "file") file: Array<MultipartFile>): UploadRsDto {
        return UploadRsDto("hui-na-blude.jpg")
    }

}