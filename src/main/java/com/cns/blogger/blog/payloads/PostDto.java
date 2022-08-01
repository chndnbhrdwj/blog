package com.cns.blogger.blog.payloads;

import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private Date created;
    private CategoryDto category;
    private UserDto user;
}
