package com.cns.blogger.blog.controllers;

import com.cns.blogger.blog.model.Category;
import com.cns.blogger.blog.payloads.ApiResponse;
import com.cns.blogger.blog.payloads.CategoryDto;
import com.cns.blogger.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(this.categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDto, categoryId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(new ApiResponse("Category Deleted with Id: "+categoryId, true));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
}
