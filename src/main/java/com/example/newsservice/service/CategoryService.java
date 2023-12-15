package com.example.newsservice.service;

import com.example.newsservice.model.Category;
import com.example.newsservice.model.Post;
import com.example.newsservice.web.model.category.CategoryFilter;

import java.util.List;

public interface CategoryService {

    List<Category> filterBy(CategoryFilter filter);

    List<Category> findAll();

    Category findById(Long id);

    Category save(Category category);

    Category update(Category category);

    void delete(Long id);

}
