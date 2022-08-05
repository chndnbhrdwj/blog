package com.cns.blogger.blog.repositories;

import com.cns.blogger.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
