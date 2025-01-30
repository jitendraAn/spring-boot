package com.blog.spring_boot_blog.services;

import com.blog.spring_boot_blog.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    /// Create
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    ///update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId);

    ///delete
    void deleteCategoryById(int categoryId);

    ///get category by Id
    CategoryDTO getCategoryById(int categoryId);

    /// get All Category
    List<CategoryDTO> getAllCategory();
}
