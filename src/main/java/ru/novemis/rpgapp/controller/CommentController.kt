package ru.novemis.rpgapp.controller

import org.springframework.web.bind.annotation.*
import ru.novemis.rpgapp.dto.announcement.CommentDto
import ru.novemis.rpgapp.dto.announcement.CommentForm
import ru.novemis.rpgapp.service.CommentService

@RestController
@RequestMapping("/announcement")
class CommentController(
        private val commentService: CommentService
) {

    @PostMapping("/comment")
    fun addComment(@RequestBody form: CommentForm): CommentDto {
        return commentService.saveComment(form)
    }

    @GetMapping("/{announcement-id}/comment")
    fun getByAnnouncementId(@PathVariable("announcement-id") announcementId: String): List<CommentDto> {
        return commentService.findByAnnouncementId(announcementId)
    }

    @DeleteMapping("/{announcement-id}/comment/{comment-id}")
    fun deleteComponent(@PathVariable("announcement-id") announcementId: String,
                        @PathVariable("comment-id") commentId: String) {
        commentService.deleteComment(commentId)
    }

    @GetMapping("/{announcement-id}/comment/{comment-id}/restore")
    fun restoreComponent(@PathVariable("announcement-id") announcementId: String,
                         @PathVariable("comment-id") commentId: String): CommentDto {
        return commentService.restoreComponent(commentId)
    }

}