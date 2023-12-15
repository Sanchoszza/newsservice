package com.example.newsservice.web.controller;

import com.example.newsservice.mapper.CategoryMapper;
import com.example.newsservice.model.Category;
import com.example.newsservice.service.CategoryService;
import com.example.newsservice.web.model.category.CategoryFilter;
import com.example.newsservice.web.model.category.CategoryListResponse;
import com.example.newsservice.web.model.category.CategoryResponse;
import com.example.newsservice.web.model.category.UpsertCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService dbCategoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping("/filter")
    public ResponseEntity<CategoryListResponse> filterBy(@Valid CategoryFilter filter){
        return ResponseEntity.ok(
                categoryMapper.categoryListToCategoryListResponse(
                        dbCategoryService.filterBy(filter)));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(){
        return ResponseEntity.ok(categoryMapper.categoryListToCategoryListResponse(
                dbCategoryService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.categoryToResponse(
                dbCategoryService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsertCategoryRequest request){

        Category newCategory = dbCategoryService.save(categoryMapper.requestToCategory(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.categoryToResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Long categoryId,
                                                   @RequestBody @Valid UpsertCategoryRequest request){

        Category updateCategory = dbCategoryService.update(categoryMapper.requestToCategory(categoryId, request));

        return ResponseEntity.ok(categoryMapper.categoryToResponse(updateCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbCategoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
