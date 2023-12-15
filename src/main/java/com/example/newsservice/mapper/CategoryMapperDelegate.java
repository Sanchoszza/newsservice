package com.example.newsservice.mapper;

import com.example.newsservice.model.Category;
import com.example.newsservice.web.model.category.UpsertCategoryRequest;

public abstract class CategoryMapperDelegate implements CategoryMapper{

    @Override
    public Category requestToCategory(UpsertCategoryRequest request) {

        Category category = new Category();
        category.setCategory(request.getCategory());

        return category;
    }

    @Override
    public Category requestToCategory(Long categoryId, UpsertCategoryRequest request) {

        Category category = requestToCategory(request);
        category.setId(categoryId);

        return category;
    }
}
