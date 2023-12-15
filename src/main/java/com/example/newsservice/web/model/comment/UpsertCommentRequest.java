package com.example.newsservice.web.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertCommentRequest {

    private Long userId;

    private Long postId;

    private String comment;
}
