package com.cns.blogger.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "post_title")
    @Size(min = 2, message = "title should be more than 2 chars")
    private String title;

    @NotEmpty
    @Column(length = 1000)
    @Size(min = 2, message = "should be more than 2 chars")
    private String content;

    private String imageName;

    private Date created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

}
