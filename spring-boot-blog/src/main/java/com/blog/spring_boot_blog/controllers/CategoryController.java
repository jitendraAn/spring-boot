package com.blog.spring_boot_blog.controllers;

import com.blog.spring_boot_blog.payloads.CategoryDTO;
import com.blog.spring_boot_blog.payloads.UserResponse;
import com.blog.spring_boot_blog.services.CategoryService;
import com.blog.spring_boot_blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        List<CategoryDTO> userDTO1 = categoryService.getAllCategory();
        return new ResponseEntity<>(userDTO1, HttpStatus.OK);
    }

}
