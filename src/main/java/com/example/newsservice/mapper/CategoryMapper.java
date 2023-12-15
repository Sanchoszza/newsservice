package com.example.newsservice.mapper;

import com.example.newsservice.model.Category;
import com.example.newsservice.web.model.category.CategoryListResponse;
import com.example.newsservice.web.model.category.CategoryResponse;
import com.example.newsservice.web.model.category.UpsertCategoryRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CategoryMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    Category requestToCategory(UpsertCategoryRequest request);

    @Mapping(source = "categoryId", target = "id")
    Category requestToCategory(Long categoryId, UpsertCategoryRequest request);

    CategoryResponse categoryToResponse(Category category);

    List<CategoryResponse> categoryListToResponseList(List<Category> categories);

    default CategoryListResponse categoryListToCategoryListResponse(List<Category> categories){
        CategoryListResponse response = new CategoryListResponse();
        response.setCategories(categoryListToResponseList(categories));

        return response;
    }
}
