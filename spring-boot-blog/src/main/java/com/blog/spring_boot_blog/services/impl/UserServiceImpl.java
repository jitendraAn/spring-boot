package com.blog.spring_boot_blog.services.impl;

import com.blog.spring_boot_blog.entites.User;
import com.blog.spring_boot_blog.exceptions.ResourceNotFoundException;
import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.repositories.UserRepo;
import com.blog.spring_boot_blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDTO createUser(UserDTO user) {
        User user1 = modelMapper.map(user, User.class);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        User user2 = userRepo.save(user1);
        return modelMapper.map(user2, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO user, int userId) {
        User user1 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + userId));
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());

        User user2 = userRepo.save(user1);

        return modelMapper.map(user2, UserDTO.class);
    }

    @Override
    public UserDTO getUserById(int userId) {
        User user1 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + userId));

        return modelMapper.map(user1, UserDTO.class);
    }

    @Override
    public UserResponse getAllUser(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<User> userPage = userRepo.findAll(pageable);

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
    public List<UserDTO> getAllUsers() {
        List<User> user = userRepo.findAll();

        return user.stream().map(e -> modelMapper.map(e, UserDTO.class)).toList();
    }

    @Override
    public void deleteUser(int userId) {
        User user1 = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + userId));
        userRepo.delete(user1);
    }
}
