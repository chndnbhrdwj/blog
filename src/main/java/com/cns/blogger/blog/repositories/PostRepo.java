package com.cns.blogger.blog.repositories;

import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.model.Post;
import com.cns.blogger.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String searchString);

    @Query("select p from Post p where p.content like :key")
    List<Post> findBySearchMethod(@Param("key") String searchString);

}
