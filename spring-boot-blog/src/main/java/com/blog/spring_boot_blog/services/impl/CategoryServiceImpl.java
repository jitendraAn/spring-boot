package com.blog.spring_boot_blog.services.impl;

import com.blog.spring_boot_blog.entites.Category;
import com.blog.spring_boot_blog.exceptions.ResourceNotFoundException;
import com.blog.spring_boot_blog.payloads.CategoryDTO;
import com.blog.spring_boot_blog.repositories.CategoryRepo;
import com.blog.spring_boot_blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category category1 = categoryRepo.save(category);

        return modelMapper.map(category1, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        Category user1 = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + categoryId));
        user1.setCategoryTitle(categoryDTO.getCategoryTitle());
        user1.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category user2 = categoryRepo.save(user1);

        return modelMapper.map(user2, CategoryDTO.class);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        Category user1 = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("userId", "Id", "" + categoryId));
        return modelMapper.map(user1, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> user = categoryRepo.findAll();
        return user.stream().map(e -> modelMapper.map(e, CategoryDTO.class)).toList();
    }
}
