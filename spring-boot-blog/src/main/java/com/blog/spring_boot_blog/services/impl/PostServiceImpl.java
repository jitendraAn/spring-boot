package com.blog.spring_boot_blog.services.impl;

import com.blog.spring_boot_blog.entites.Category;
import com.blog.spring_boot_blog.entites.Post;
import com.blog.spring_boot_blog.entites.User;
import com.blog.spring_boot_blog.exceptions.ResourceNotFoundException;
import com.blog.spring_boot_blog.payloads.PostDTO;
import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.repositories.CategoryRepo;
import com.blog.spring_boot_blog.repositories.PostRepo;
import com.blog.spring_boot_blog.repositories.UserRepo;
import com.blog.spring_boot_blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        Post post1 = postRepo.save(post);

        return modelMapper.map(post1, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, int postId) {
        return null;
    }

    @Override
    public void deletePostById(int postId) {

    }

    @Override
    public PostDTO getPostById(int postId) {
        Post user1 = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + postId));
        return modelMapper.map(user1, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> user = postRepo.findAll();
        return user.stream().map(e -> modelMapper.map(e, PostDTO.class)).toList();
    }

    @Override
    public UserResponse getAllPost(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<Post> userPage = postRepo.findAll(pageable);

        List<UserDTO> user = userPage.get().map(i -> modelMapper.map(i, UserDTO.class)).toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setUserDTOList(user);
        userResponse.setPageNo(userPage.getNumber());
        userResponse.setPageSize(userPage.getSize());
        userResponse.setTotalPageElement(userPage.getTotalElements());
        userResponse.setTotalPageSize(userPage.getTotalPages());
        userResponse.setLastPage(userPage.isLast());


        return userResponse;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO, int userId, int categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + userId));

        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("categoryId", "Id", "" + categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setAddedDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        Post post1 = postRepo.save(post);

        return modelMapper.map(post1, PostDTO.class);
    }
}
