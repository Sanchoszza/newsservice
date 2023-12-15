package com.example.newsservice.mapper;

import com.example.newsservice.model.Comment;
import com.example.newsservice.service.PostService;
import com.example.newsservice.service.UserService;
import com.example.newsservice.web.model.comment.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper{

    @Autowired
    private UserService dbUserService;

    @Autowired
    private PostService dbPostService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {

        Comment comment = new Comment();
        comment.setComment(request.getComment());
        comment.setUser(dbUserService.findById(request.getUserId()));
        comment.setPost(dbPostService.findById(request.getUserId()));

        return comment;
    }

    @Override
    public Comment requestToComment(Long commentId, UpsertCommentRequest request) {

        Comment comment = requestToComment(request);
        comment.setId(commentId);

        return comment;
    }
}
