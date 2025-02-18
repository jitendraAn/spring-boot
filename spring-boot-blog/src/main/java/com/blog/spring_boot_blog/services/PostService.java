package com.blog.spring_boot_blog.services;

import com.blog.spring_boot_blog.payloads.PostDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;

import java.util.List;

public interface PostService {

    /// Create
    PostDTO createPost(PostDTO postDTO);

    ///update
    PostDTO updatePost(PostDTO postDTO, int postId);

    ///delete
    void deletePostById(int postId);

    ///get category by Id
    PostDTO getPostById(int categoryId);

    /// get All Category
    List<PostDTO> getAllPost();

    UserResponse getAllPost(int pageNo, int pageSize);

    PostDTO createPost(PostDTO postDTO, int userId, int categoryId);
}
