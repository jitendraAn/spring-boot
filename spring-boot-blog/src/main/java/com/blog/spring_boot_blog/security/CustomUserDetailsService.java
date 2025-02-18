package com.blog.spring_boot_blog.security;

import com.blog.spring_boot_blog.entites.User;
import com.blog.spring_boot_blog.exceptions.ResourceNotFoundException;
import com.blog.spring_boot_blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(username).get();
//User user= userRepo.findByEmail(username)
//                .orElseThrow(() -> new ResourceNotFoundException("user", "Email" + username, "0"));

        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword())
                .build();
   // return  user1.get();

    }
}
