package com.example.newsservice.repository;

import com.example.newsservice.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DatabaseCategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Override
    @EntityGraph(attributePaths = {"posts"})
    List<Category> findAll();

    Page<Category> findAllByCategory(String category, Pageable pageable);
}
