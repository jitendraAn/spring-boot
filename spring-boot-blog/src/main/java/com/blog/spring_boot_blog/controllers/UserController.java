package com.blog.spring_boot_blog.controllers;

import com.blog.spring_boot_blog.payloads.ResponseObject;
import com.blog.spring_boot_blog.payloads.UserDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.security.CustomUserDetailsService;
import com.blog.spring_boot_blog.security.JwtTokenHelper;
import com.blog.spring_boot_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService myUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtUtil;

    @PostMapping("/saveUser")
    public ResponseEntity<ResponseObject> createUser(@RequestBody UserDTO userDTO) {

        ResponseObject response = new ResponseObject();
        UserDTO userDTO1 = null;
        try {

//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
//            );

           // final UserDetails userDetails = myUserDetailService.loadUserByUsername(userDTO.getEmail());
         //   if(userDetails!=null) {
                String token = jwtUtil.generateToken(userDTO.getEmail());


                userDTO1 = userService.createUser(userDTO);
                userDTO1.setToken(token);
                response.setResponseData(userDTO1);
                response.setResponseStatus(true);
                response.setResponseMessage("Token generate successfully");
//            }else{
//                response.setResponseStatus(false);
//                response.setResponseMessage("user details not found");
//            }

        } catch (AuthenticationException e) {
            response.setResponseStatus(false);
            response.setResponseMessage("Invalid username or password");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserDTO userDTO) {

        ResponseObject response = new ResponseObject();
       // UserDTO userDTO1 = null;
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );

             final UserDetails userDetails = myUserDetailService.loadUserByUsername(userDTO.getEmail());
               if(userDetails!=null) {
            String token = jwtUtil.generateToken(userDTO.getEmail());

            response.setResponseData(token);
            response.setResponseStatus(true);
            response.setResponseMessage("Login successfully");
            }else{
                response.setResponseStatus(false);
                response.setResponseMessage("user details not found");
            }

        } catch (AuthenticationException e) {
            response.setResponseStatus(false);
            response.setResponseMessage("Invalid username or password");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
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
