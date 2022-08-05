package com.cns.blogger.blog.payloads;

import com.cns.blogger.blog.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private List<CommentDto> comments = new ArrayList<>();
}
