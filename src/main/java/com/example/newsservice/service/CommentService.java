package com.example.newsservice.service;

import com.example.newsservice.exception.UpdateStateException;
import com.example.newsservice.model.Comment;
import com.example.newsservice.web.model.comment.CommentFilter;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public interface CommentService {

    List<Comment> filterBy(CommentFilter filter);

    List<Comment> findAll();

    Comment findById(Long id);

    Comment save(Comment comment);

    Comment update(Comment comment);

    void delete(Long id);

    default void checkForUpdate(Long commentId){
        Comment currentComment = findById(commentId);
        Instant now = Instant.now();

        Duration duration = Duration.between(currentComment.getUpdateAt(), now);

        if (duration.getSeconds() > 500){
            throw new UpdateStateException("Impossible to update comment");
        }
    }
}
