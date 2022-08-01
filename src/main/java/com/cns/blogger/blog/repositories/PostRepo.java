package com.cns.blogger.blog.repositories;

import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
