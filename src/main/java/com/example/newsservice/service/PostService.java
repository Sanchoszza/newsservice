package com.example.newsservice.service;

import com.example.newsservice.exception.UpdateStateException;
import com.example.newsservice.model.Category;
import com.example.newsservice.model.Comment;
import com.example.newsservice.model.Post;
import com.example.newsservice.web.model.post.PostFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface PostService {

    List<Post> filterBy(PostFilter filter);

    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);

    Post update(Post post);

    void delete(Long id);

    Post saveWithCategory(Post post, List<Category> categories);

    default void checkForUpdate(Long postId){
        Post currentPost = findById(postId);
        Instant now = Instant.now();

        Duration duration = Duration.between(currentPost.getUpdateAt(), now);

        if (duration.getSeconds() > 500){
            throw new UpdateStateException("Impossible to update post");
        }
    }
}
