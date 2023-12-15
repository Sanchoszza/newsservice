package com.example.newsservice.repository.specification;

import com.example.newsservice.model.Category;
import com.example.newsservice.web.model.category.CategoryFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface CategorySpecification {

    static Specification<Category> withFilter(CategoryFilter categoryFilter){
        return Specification.where(byCategory(categoryFilter.getCategory()))
                .and(byPostId(categoryFilter.getPostId()))
                .and(byCreatedAtBefore(categoryFilter.getCratedBefore()))
                .and(byUpdatedAtBefore(categoryFilter.getUpdatedBefore()));
    }

    static Specification<Category> byCategory(String category){
        return ((root, query, criteriaBuilder) -> {
            if (category == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("category"), category);
        });
    }

    static Specification<Category> byPostId(Long postId){
        return ((root, query, criteriaBuilder) -> {
            if (postId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("post").get("id"), postId);
        });
    }

    static Specification<Category> byCreatedAtBefore(Instant createdBefore){
        return ((root, query, criteriaBuilder) -> {
            if (createdBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("createAt"), createdBefore);
        });
    }

    static Specification<Category> byUpdatedAtBefore(Instant updatedBefore){
        return ((root, query, criteriaBuilder) -> {
            if (updatedBefore == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("updateAt"), updatedBefore);
        });
    }
}
