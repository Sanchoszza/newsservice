package com.example.newsservice.repository.specification;

import com.example.newsservice.model.Comment;
import com.example.newsservice.web.model.comment.CommentFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface CommentSpecification {

    static Specification<Comment> withFilter(CommentFilter commentFilter){
        return Specification.where(byComment(commentFilter.getComment()))
                .and(byUserId(commentFilter.getUserId()))
                .and(byPostId(commentFilter.getPostId()))
                .and(byCreatedAtBefore(commentFilter.getCreatedBefore()))
                .and(byUpdatedAtBefore(commentFilter.getUpdatedBefore()));
    }

    static Specification<Comment> byComment(String comment){
        return ((root, query, criteriaBuilder) -> {
            if (comment == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("comment"), comment);
        });
    }

    static Specification<Comment> byUserId(Long userId){
        return ((root, query, criteriaBuilder) -> {
            if (userId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        });
    }

    static Specification<Comment> byPostId(Long postId){
        return ((root, query, criteriaBuilder) -> {
            if (postId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("post").get("id"), postId);
        });
    }

    static Specification<Comment> byCreatedAtBefore(Instant createdBefore){
        return ((root, query, criteriaBuilder) -> {
            if (createdBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("createAt"), createdBefore);
        });
    }

    static Specification<Comment> byUpdatedAtBefore(Instant updatedBefore){
        return ((root, query, criteriaBuilder) -> {
            if (updatedBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("updateAt"), updatedBefore);
        });
    }

}
