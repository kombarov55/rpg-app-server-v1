package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.announcement.CommentRqDto
import ru.novemis.rpgapp.dto.announcement.CommentRsDto
import ru.novemis.rpgapp.service.CommentService

@RestController
@RequestMapping("/announcement/comment")
class CommentController(
        private val commentService: CommentService
) {

    @PostMapping
    fun addComment(@RequestBody rq: CommentRqDto): CommentRsDto {
        return commentService.saveComment(rq)
    }

    @GetMapping
    fun getAll(): List<CommentRsDto> {
        return commentService.findAllComments()
    }

}