package com.cns.blogger.blog.controllers;

import com.cns.blogger.blog.payloads.ApiResponse;
import com.cns.blogger.blog.payloads.PostDto;
import com.cns.blogger.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        return new ResponseEntity<>(this.postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@Valid @PathVariable Integer userId) {
        return ResponseEntity.ok(this.postService.getAllPostByUser(userId));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@Valid @PathVariable Integer categoryId) {
        return ResponseEntity.ok(this.postService.getAllPostByCategory(categoryId));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(this.postService.getAllPost());
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostsById(@Valid @PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.getPost(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePostsById(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.updatePost(postDto, postId));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePostsById(@Valid @PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return ResponseEntity.ok(new ApiResponse(String.format("Post with id %s deleted successfully", postId), true));
    }
}
