package com.blog.spring_boot_blog.repositories;

import com.blog.spring_boot_blog.entites.Category;
import com.blog.spring_boot_blog.entites.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
}
