package com.example.newsservice.service.impl;

import com.example.newsservice.aop.Loggable;
import com.example.newsservice.exception.EntityNotFoundException;
import com.example.newsservice.model.Category;
import com.example.newsservice.model.Post;
import com.example.newsservice.model.User;
import com.example.newsservice.repository.DatabaseCategoryRepository;
import com.example.newsservice.repository.DatabasePostRepository;
import com.example.newsservice.repository.specification.PostSpecification;
import com.example.newsservice.service.CategoryService;
import com.example.newsservice.service.PostService;
import com.example.newsservice.service.UserService;
import com.example.newsservice.utils.BeanUtils;
import com.example.newsservice.web.model.post.PostFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final DatabasePostRepository postRepository;

    private final DatabaseCategoryRepository categoryRepository;

    private final CategoryService categoryService;

    private final UserService userService;

    @Override
    public List<Post> filterBy(PostFilter filter) {
        return postRepository.findAll(PostSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(MessageFormat.format(
                        "Post with ID {0} not found ", id
                )));
    }

    @Override
    public Post save(Post post) {

        Category category =categoryService.findById(post.getCategory().getId());
        User user = userService.findById(post.getUser().getId());
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        checkForUpdate(post.getId());
        Post existedPost = findById(post.getId());

        BeanUtils.copyNonNullProperties(post, existedPost);

        return postRepository.save(existedPost);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Loggable
    public Post saveWithCategory(Post post, List<Category> categories) {
        Category category = post.getCategory();
        if (category != null) {
            Category existingCategory = categoryService.findById(category.getId());
            post.setCategory(existingCategory);
        }

        User user = post.getUser();
        if (user != null) {
            User existingUser = userService.findById(user.getId());
            post.setUser(existingUser);
        }

        Post savedPost = postRepository.save(post);

        for (Category savedCategory : categories) {
            Category existingCategory = categoryService.findById(savedCategory.getId());
            savedPost.addCategory(existingCategory);
        }

        return savedPost;
    }

}
