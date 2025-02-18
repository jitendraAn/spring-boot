package com.blog.spring_boot_blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    private String token;


}
