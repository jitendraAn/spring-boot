package com.blog.spring_boot_blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {
    private List<UserDTO> userDTOList;
    private List<PostDTO>  postDTOList;
    private int pageNo;
    private int pageSize;
    private long totalPageElement;
    private int totalPageSize;
    private boolean isLastPage;

}
