package com.cns.blogger.blog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    @NotEmpty
    private String categoryTitle;

    @Column(name = "description")
    @NotEmpty
    private String categoryDescription;
}
