package com.example.newsservice.web.model.category;

import com.example.newsservice.validation.CategoryFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@CategoryFilterValid
public class CategoryFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String category;

    private Instant cratedBefore;

    private Instant updatedBefore;

    private Long postId;
}
