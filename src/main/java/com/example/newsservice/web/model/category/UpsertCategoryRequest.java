package com.example.newsservice.web.model.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertCategoryRequest {

    @NotBlank(message = "Category have to be field in")
    @Size(min = 3, max = 30, message = "Category cant be less {min} and more {max}")
    private String category;
}
