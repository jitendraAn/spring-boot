package com.blog.spring_boot_blog.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

//    @ManyToOne
//    private Category category;
//
//    @ManyToOne
//    private  User user;
}
