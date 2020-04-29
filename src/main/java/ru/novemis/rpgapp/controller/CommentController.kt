package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.announcement.CommentRqDto
import ru.novemis.rpgapp.dto.announcement.CommentRsDto
import ru.novemis.rpgapp.service.CommentService

@RestController
@RequestMapping("/announcement")
class CommentController(
        private val commentService: CommentService
) {

    @PostMapping("/comment")
    fun addComment(@RequestBody rq: CommentRqDto): CommentRsDto {
        return commentService.saveComment(rq)
    }

    @GetMapping("/{announcement-id}/comment")
    fun getByAnnouncementId(@PathVariable("announcement-id") announcementId: String): List<CommentRsDto> {
        return commentService.findByAnnouncementId(announcementId)
    }

    @DeleteMapping("/{announcement-id}/comment/{comment-id}")
    fun deleteComponent(@PathVariable("announcement-id") announcementId: String,
                        @PathVariable("comment-id") commentId: String) {
        commentService.deleteComment(commentId)
    }

    @GetMapping("/{announcement-id}/comment/{comment-id}/restore")
    fun restoreComponent(@PathVariable("announcement-id") announcementId: String,
                         @PathVariable("comment-id") commentId: String): CommentRsDto {
        return commentService.restoreComponent(commentId)
    }

}