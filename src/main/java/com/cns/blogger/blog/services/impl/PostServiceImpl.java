package com.cns.blogger.blog.services.impl;

import com.cns.blogger.blog.exceptions.ResourceNotFoundException;
import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.model.User;
import com.cns.blogger.blog.payloads.PostDto;
import com.cns.blogger.blog.repositories.CategoryRepo;
import com.cns.blogger.blog.repositories.PostRepo;
import com.cns.blogger.blog.repositories.UserRepo;
import com.cns.blogger.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setCreated(new Date());
        post.setUser(user);
        post.setCategory(category);
        return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPost(Integer postId) {
        return this.modelMapper.map(this.postRepo.findById(postId), PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        return this.postRepo.findAll()
                .stream().map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return this.postRepo.findByCategory(category)
                .stream().map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return this.postRepo.findByUser(user)
                .stream().map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(Integer userId) {
        return null;
    }
}