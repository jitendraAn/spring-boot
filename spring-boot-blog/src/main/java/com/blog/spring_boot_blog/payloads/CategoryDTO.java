package com.blog.spring_boot_blog.payloads;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Data
public class CategoryDTO {
    private int categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
