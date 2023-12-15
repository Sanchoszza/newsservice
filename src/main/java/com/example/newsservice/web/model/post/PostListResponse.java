package com.example.newsservice.web.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostListResponse {

    private List<PostResponse> posts = new ArrayList<>();
}
