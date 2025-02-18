package com.blog.spring_boot_blog.payloads;

import com.blog.spring_boot_blog.entites.Category;
import com.blog.spring_boot_blog.entites.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostDTO {

    private int postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;

    private CategoryDTO category;

    private UserDTO user;
}
