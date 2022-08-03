package com.cns.blogger.blog.controllers;

import com.cns.blogger.blog.payloads.ApiResponse;
import com.cns.blogger.blog.payloads.PostDto;
import com.cns.blogger.blog.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getPostsByUser(@Valid @PathVariable Integer userId,
                                                       @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                       @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        return ResponseEntity.ok(this.postService.getAllPostByUser(userId, pageNumber, pageSize));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostsByCategory(@Valid @PathVariable Integer categoryId,
                                                           @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        return ResponseEntity.ok(this.postService.getAllPostByCategory(categoryId, pageNumber, pageSize));
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirectionAsc", defaultValue = "true", required = false) boolean sortDirectionAsc,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize) {
        return ResponseEntity.ok(this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDirectionAsc));
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

    @GetMapping("/posts/search/{searchString}")
    public ResponseEntity<List<PostDto>> searchPosts(@Valid @PathVariable String searchString) {
        return ResponseEntity.ok(this.postService.searchPost(searchString));
    }

    @GetMapping("/posts/search/content/{searchString}")
    public ResponseEntity<List<PostDto>> searchPostsByQuery(@Valid @PathVariable String searchString) {
        return ResponseEntity.ok(this.postService.searchPostContent("%"+searchString+"%"));
    }
}
