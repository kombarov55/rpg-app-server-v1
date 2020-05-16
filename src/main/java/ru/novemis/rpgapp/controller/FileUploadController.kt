package ru.novemis.rpgapp.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.novemis.rpgapp.dto.common.UploadRsDto
import ru.novemis.rpgapp.repository.announcement.UploadCacheRepository
import java.io.File

@RestController
@RequestMapping("/upload")
class FileUploadController(
        @Value("\${upload-dir}")
        private val uploadDir: String,

        private val uploadCacheRepository: UploadCacheRepository
) {

    @PostMapping
    fun upload(@RequestParam("file") file: MultipartFile): UploadRsDto {
        return UploadRsDto("hui-na-blude.jpg")
    }

}