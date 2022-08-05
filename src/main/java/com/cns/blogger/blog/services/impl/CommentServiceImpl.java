package com.cns.blogger.blog.services.impl;

import com.cns.blogger.blog.exceptions.ResourceNotFoundException;
import com.cns.blogger.blog.model.Comment;
import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.payloads.CommentDto;
import com.cns.blogger.blog.repositories.CommentRepo;
import com.cns.blogger.blog.repositories.PostRepo;
import com.cns.blogger.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        return this.modelMapper.map(this.commentRepo.save(comment), CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepo.delete(comment);
    }
}
