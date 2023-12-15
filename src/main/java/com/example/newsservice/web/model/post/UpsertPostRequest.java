package com.example.newsservice.web.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertPostRequest {

    private Long userId;

    private Long categoryId;

    private String post;
}
