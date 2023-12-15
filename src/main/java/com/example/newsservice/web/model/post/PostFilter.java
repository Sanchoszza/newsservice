package com.example.newsservice.web.model.post;

import com.example.newsservice.validation.PostFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@PostFilterValid
public class PostFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String post;

    private Instant createdBefore;

    private Instant updatedBefore;

    private Long userId;

    private Long categoryId;
}
