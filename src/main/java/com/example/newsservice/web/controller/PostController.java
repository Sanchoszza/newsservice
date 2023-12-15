package com.example.newsservice.web.controller;

import com.example.newsservice.mapper.PostMapper;
import com.example.newsservice.model.Category;
import com.example.newsservice.model.Post;
import com.example.newsservice.service.PostService;
import com.example.newsservice.web.model.post.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService dbPostService;

    private final PostMapper postMapper;

    @GetMapping("/filter")
    public ResponseEntity<PostListResponse> filterBy(@Valid PostFilter filter){
        return ResponseEntity.ok(
                postMapper.postListToPostListResponse(
                        dbPostService.filterBy(filter)));
    }

    @GetMapping
    public ResponseEntity<PostListResponse> findAll(){
        return ResponseEntity.ok(
                postMapper.postListToPostListResponse(
                        dbPostService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(postMapper.postToResponse(dbPostService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody @Valid UpsertPostRequest request){

        Post newPost = dbPostService.save(postMapper.requestToPost(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postMapper.postToResponse(newPost));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(@PathVariable("id") Long postId,
                                               @RequestBody @Valid UpsertPostRequest request){
        Post updatePost = dbPostService.update(postMapper.requestToPost(postId, request));

        return ResponseEntity.ok(postMapper.postToResponse(updatePost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbPostService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save-with-category")
    public ResponseEntity<PostResponse> createWithCategory(@RequestBody CreatePostWithCategoryRequest request){
        Post post = Post.builder().post(request.getPost()).build();
        List<Category> categories = request.getCategories().stream()
                .map(categoryRequest -> Category.builder()
                        .category(categoryRequest.getCategory()).build())
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postMapper.postToResponse(dbPostService.saveWithCategory(post, categories)));
    }
}
