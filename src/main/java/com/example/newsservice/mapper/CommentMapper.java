package com.example.newsservice.mapper;

import com.example.newsservice.model.Comment;
import com.example.newsservice.web.model.comment.CommentListResponse;
import com.example.newsservice.web.model.comment.CommentResponse;
import com.example.newsservice.web.model.comment.UpsertCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    List<CommentResponse> commentListToResponseList(List<Comment> comments);

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments){

        CommentListResponse response = new CommentListResponse();
        response.setComments(commentListToResponseList(comments));

        return  response;
    }
}
