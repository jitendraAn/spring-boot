package com.blog.spring_boot_blog.controllers;

import com.blog.spring_boot_blog.payloads.PostDTO;
import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/savePost")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO,
                                              @RequestParam int userId,
                                              @RequestParam int categoryId
    ) {
        PostDTO userDTO1 = postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO userDTO1 = postService.getPostById(id);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<UserResponse> getAllPost(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        UserResponse userDTO1 = postService.getAllPost(pageNo,pageSize);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }
}
