package com.example.newsservice.repository;

import com.example.newsservice.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DatabaseUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Override
    @EntityGraph(attributePaths = {"posts"})
    List<User> findAll();

    Page<User> findAllByName(String name, Pageable pageable);
}
