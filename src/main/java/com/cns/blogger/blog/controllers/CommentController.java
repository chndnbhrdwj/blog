package com.cns.blogger.blog.controllers;

import com.cns.blogger.blog.payloads.ApiResponse;
import com.cns.blogger.blog.payloads.CommentDto;
import com.cns.blogger.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        return new ResponseEntity<>(this.commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@Valid @PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse("Comment deleted successfully", true));
    }
}
