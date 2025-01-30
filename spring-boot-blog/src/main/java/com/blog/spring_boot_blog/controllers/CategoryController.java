package com.blog.spring_boot_blog.controllers;

import com.blog.spring_boot_blog.payloads.CategoryDTO;
import com.blog.spring_boot_blog.services.CategoryService;
import com.blog.spring_boot_blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/CreateCategory")
    public ResponseEntity createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO);

        return new ResponseEntity<>(categoryDTO1, HttpStatus.CREATED);
    }


}
