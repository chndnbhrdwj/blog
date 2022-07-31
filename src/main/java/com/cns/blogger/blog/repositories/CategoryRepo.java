package com.cns.blogger.blog.repositories;

import com.cns.blogger.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
