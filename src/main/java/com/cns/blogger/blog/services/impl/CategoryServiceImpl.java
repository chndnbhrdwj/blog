package com.cns.blogger.blog.services.impl;

import com.cns.blogger.blog.exceptions.ResourceNotFoundException;
import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.payloads.CategoryDto;
import com.cns.blogger.blog.repositories.CategoryRepo;
import com.cns.blogger.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        return this.modelMapper.map(this.categoryRepo.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return this.modelMapper.map(this.categoryRepo.save(category), CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        return this.categoryRepo.findAll()
                .stream().map(category -> this.modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
