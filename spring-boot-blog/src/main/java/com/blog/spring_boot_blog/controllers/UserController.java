package com.blog.spring_boot_blog.controllers;

import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTO1 = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO1, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        UserDTO userDTO1 = userService.getUserById(id);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<UserResponse> getAllUser(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        UserResponse userDTO1 = userService.getAllUser(pageNo,pageSize);
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }
}
