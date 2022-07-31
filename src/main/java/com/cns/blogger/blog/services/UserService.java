package com.cns.blogger.blog.services;

import com.cns.blogger.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUser(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
