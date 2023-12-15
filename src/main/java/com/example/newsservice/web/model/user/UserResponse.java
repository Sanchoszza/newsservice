package com.example.newsservice.web.model.user;

import com.example.newsservice.web.model.comment.CommentResponse;
import com.example.newsservice.web.model.post.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private List<PostResponse> posts = new ArrayList<>();
    private List<CommentResponse> comments = new ArrayList<>();
}
