package com.example.newsservice.mapper;

import com.example.newsservice.exception.EntityNotFoundException;
import com.example.newsservice.model.Category;
import com.example.newsservice.model.Post;
import com.example.newsservice.model.User;
import com.example.newsservice.service.CategoryService;
import com.example.newsservice.service.UserService;
import com.example.newsservice.web.model.post.UpsertPostRequest;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class PostMapperDelegate implements PostMapper{

    @Autowired
    private UserService dbUserService;

    @Autowired
    private CategoryService dbCategoryService;

    @Override
    public Post requestToPost(UpsertPostRequest request) {

        Post post = new Post();
        post.setUser(dbUserService.findById(request.getUserId()));
        post.setCategory(dbCategoryService.findById(request.getCategoryId()));
        post.setPost(request.getPost());

        return post;
    }

    @Override
    public Post requestToPost(Long postId, UpsertPostRequest request) {
        Post post = requestToPost(request);
        post.setId(postId);

        return post;
    }
}



//        Long categoryId = request.getCategoryId();
//        Long userId = request.getUserId();
//
//        Category category = dbCategoryService.findById(categoryId);
//        User user = dbUserService.findById(userId);
//
////        request.setCategoryId(categoryId);
////        request.setUserId(userId);
//
//        if (categoryId == null) {
//            throw new IllegalArgumentException("Category ID cannot be null");
//        }
//
//        if (userId == null) {
//            throw new IllegalArgumentException("User ID cannot be null");
//        }
//
//        if (category == null) {
//            throw new EntityNotFoundException("Category with ID " + categoryId + " not found");
//        }
//
//
//        if (user == null) {
//            throw new EntityNotFoundException("User with ID " + userId + " not found");
//        }