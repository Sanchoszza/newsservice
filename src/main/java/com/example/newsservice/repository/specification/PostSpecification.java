package com.example.newsservice.repository.specification;

import com.example.newsservice.model.Post;
import com.example.newsservice.web.model.post.PostFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface PostSpecification {

    static Specification<Post> withFilter(PostFilter postFilter){
        return Specification.where(byPost(postFilter.getPost()))
                .and(byUserId(postFilter.getUserId()))
                .and(byCategoryId(postFilter.getCategoryId()))
                .and(byCreatedAtBefore(postFilter.getCreatedBefore()))
                .and(byUpdatedAtBefore(postFilter.getUpdatedBefore()));
    }

    static Specification<Post> byPost(String post){
        return ((root, query, criteriaBuilder) -> {
            if (post == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("post"), post);
        });
    }

    static Specification<Post> byUserId(Long userId){
        return ((root, query, criteriaBuilder) -> {
            if (userId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("user").get("id"), userId);
        });
    }

    static Specification<Post> byCategoryId(Long categoryId){
        return ((root, query, criteriaBuilder) -> {
            if (categoryId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        });
    }

    static Specification<Post> byCreatedAtBefore(Instant createdBefore){
        return ((root, query, criteriaBuilder) -> {
            if (createdBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("createAt"), createdBefore);
        });
    }

    static Specification<Post> byUpdatedAtBefore(Instant updatedBefore){
        return ((root, query, criteriaBuilder) -> {
            if (updatedBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("updateAt"), updatedBefore);
        });
    }
}
