package com.cns.blogger.blog.repositories;

import com.cns.blogger.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
