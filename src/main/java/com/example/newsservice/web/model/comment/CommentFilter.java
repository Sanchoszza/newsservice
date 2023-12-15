package com.example.newsservice.web.model.comment;

import com.example.newsservice.validation.CommentFilterValid;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
@CommentFilterValid
public class CommentFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String comment;

    private Instant createdBefore;

    private Instant updatedBefore;

    private Long userId;

    private Long postId;
}
