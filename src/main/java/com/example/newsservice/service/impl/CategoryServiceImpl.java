package com.example.newsservice.service.impl;

import com.example.newsservice.aop.Loggable;
import com.example.newsservice.exception.EntityNotFoundException;
import com.example.newsservice.model.Category;
import com.example.newsservice.model.Post;
import com.example.newsservice.repository.DatabaseCategoryRepository;
import com.example.newsservice.repository.DatabasePostRepository;
import com.example.newsservice.repository.specification.CategorySpecification;
import com.example.newsservice.repository.specification.PostSpecification;
import com.example.newsservice.service.CategoryService;
import com.example.newsservice.utils.BeanUtils;
import com.example.newsservice.web.model.category.CategoryFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final DatabaseCategoryRepository categoryRepository;

    private final DatabasePostRepository postRepository;

    @Override
    public List<Category> filterBy(CategoryFilter filter) {
        return categoryRepository.findAll(CategorySpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Category with ID {0} not found", id
                )));
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        Category existedCategory = findById(category.getId());
        BeanUtils.copyNonNullProperties(category, existedCategory);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
