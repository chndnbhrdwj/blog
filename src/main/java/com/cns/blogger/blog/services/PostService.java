package com.cns.blogger.blog.services;


import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    void deletePost(Integer postId);
    PostDto getPost(Integer postId);
    List<PostDto> getAllPost();
    List<PostDto> getAllPostByCategory(Integer categoryId);
    List<PostDto> getAllPostByUser(Integer userId);
    List<PostDto> searchPost(Integer userId);


}
