package com.example.newsservice.repository;

import com.example.newsservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DatabaseCommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    Page<Comment> findAllByComment(String comment, Pageable pageable);
}
