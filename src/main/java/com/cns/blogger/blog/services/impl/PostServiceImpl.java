package com.cns.blogger.blog.services.impl;

import com.cns.blogger.blog.exceptions.ResourceNotFoundException;
import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.model.User;
import com.cns.blogger.blog.payloads.PostDto;
import com.cns.blogger.blog.payloads.PostResponse;
import com.cns.blogger.blog.repositories.CategoryRepo;
import com.cns.blogger.blog.repositories.PostRepo;
import com.cns.blogger.blog.repositories.UserRepo;
import com.cns.blogger.blog.services.PostService;
import javafx.geometry.Pos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        post.setImageName(postDto.getImageName());
        return this.modelMapper.map(this.postRepo.save(post), PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostDto getPost(Integer postId) {
        return this.modelMapper.map(this.postRepo.findById(postId), PostDto.class);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, boolean sortAsc) {
        PostResponse postResponse = new PostResponse();
        Sort sort = Sort.by(sortBy);
        sort = (sortAsc) ? sort.ascending(): sort.descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> posts = this.postRepo.findAll(pageable);

        List<PostDto> content = posts.getContent()
                .stream().map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());

        postResponse.setContent(content);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalRecords(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        PostResponse postResponse = new PostResponse();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> allPost = this.postRepo.findAll(pageable);

        List<PostDto> content = allPost
                .stream()
                .filter(p -> p.getCategory().getId() == categoryId)
                .map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());

        postResponse.setContent(content);
        postResponse.setLastPage(allPost.isLast());
        postResponse.setTotalRecords(allPost.getNumberOfElements());
        postResponse.setTotalPages(allPost.getTotalPages());
        postResponse.setPageNumber(allPost.getNumber());
        postResponse.setPageSize(allPost.getSize());

        return postResponse;
    }

    @Override
    public PostResponse getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {
        PostResponse postResponse = new PostResponse();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Post> allPost = this.postRepo.findAll(pageable);

        List<PostDto> content = allPost
                .stream()
                .filter(p -> p.getUser().getId() == userId)
                .map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());

        postResponse.setContent(content);
        postResponse.setLastPage(allPost.isLast());
        postResponse.setTotalRecords(allPost.getNumberOfElements());
        postResponse.setTotalPages(allPost.getTotalPages());
        postResponse.setPageNumber(allPost.getNumber());
        postResponse.setPageSize(allPost.getSize());

        return postResponse;
    }

    @Override
    public List<PostDto> searchPost(String searchString) {
        return this.postRepo.findByTitleContaining(searchString).stream()
                .map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPostContent(String searchString) {
        return this.postRepo.findBySearchMethod(searchString).stream()
                .map(p -> this.modelMapper.map(p, PostDto.class))
                .collect(Collectors.toList());
    }
}
