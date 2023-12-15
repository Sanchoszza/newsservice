package com.example.newsservice.web.controller;

import com.example.newsservice.mapper.CommentMapper;
import com.example.newsservice.model.Comment;
import com.example.newsservice.service.CommentService;
import com.example.newsservice.web.model.comment.CommentFilter;
import com.example.newsservice.web.model.comment.CommentListResponse;
import com.example.newsservice.web.model.comment.CommentResponse;
import com.example.newsservice.web.model.comment.UpsertCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService dbCommentService;

    private final CommentMapper commentMapper;

    @GetMapping("/filter")
    public ResponseEntity<CommentListResponse> filterBy(@Valid CommentFilter filter){
        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(dbCommentService.filterBy(filter))
        );
    }

    @GetMapping
    public ResponseEntity<CommentListResponse> findAll(){
        return ResponseEntity.ok(commentMapper.commentListToCommentListResponse(
                dbCommentService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentMapper.commentToResponse(
                dbCommentService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request){

        Comment newComment = dbCommentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(newComment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long commentId,
                                                  @RequestBody @Valid UpsertCommentRequest request){

        Comment updateComment = dbCommentService.update(commentMapper.requestToComment(commentId, request));

        return ResponseEntity.ok(commentMapper.commentToResponse(updateComment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbCommentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
