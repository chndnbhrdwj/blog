package com.cns.blogger.blog.services;

import com.cns.blogger.blog.payloads.PostDto;
import com.cns.blogger.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostDto getPost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, boolean sortAsc);
    PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
    PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);
    List<PostDto> searchPost(String searchString);
    List<PostDto> searchPostContent(String searchString);


}
