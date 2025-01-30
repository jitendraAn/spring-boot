package com.blog.spring_boot_blog.services;


import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,int userId);
    UserDTO getUserById(int userId);
    UserResponse getAllUser(int pageNo, int pageSize);
    List<UserDTO> getAllUsers();
    void deleteUser(int userId);


}
