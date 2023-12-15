package com.example.newsservice.web.model.post;

import com.example.newsservice.web.model.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePostWithCategoryRequest {

    private String post;

    private List<CategoryResponse> categories;
}
