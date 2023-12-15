package com.example.newsservice.service.impl;

import com.example.newsservice.exception.EntityNotFoundException;
import com.example.newsservice.model.Comment;
import com.example.newsservice.model.Post;
import com.example.newsservice.model.User;
import com.example.newsservice.repository.DatabaseCommentRepository;
import com.example.newsservice.repository.specification.CommentSpecification;
import com.example.newsservice.service.CommentService;
import com.example.newsservice.service.PostService;
import com.example.newsservice.service.UserService;
import com.example.newsservice.utils.BeanUtils;
import com.example.newsservice.web.model.comment.CommentFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final DatabaseCommentRepository commentRepository;

    private final UserService userService;

    private final PostService postService;

    @Override
    public List<Comment> filterBy(CommentFilter filter) {
        return commentRepository.findAll(CommentSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(MessageFormat.format(
                        "Comment with ID {0} not found ", id
                )));
    }

    @Override
    public Comment save(Comment comment) {

        User user = comment.getUser();
        if (user != null) {
            User existingUser = userService.findById(user.getId());
            comment.setUser(existingUser);
        }

        Post post = comment.getPost();
        if (post != null) {
            Post existingPost = postService.findById(post.getId());
            post.setPost(String.valueOf(existingPost));
        }

        return commentRepository.save(comment);

//        User user = userService.findById(comment.getUser().getId());
//        Post post = postService.findById(comment.getPost().getId());
//        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        checkForUpdate(comment.getId());
        User user = userService.findById(comment.getUser().getId());
        Post post = postService.findById(comment.getPost().getId());
        Comment existedComment = findById(comment.getId());

        BeanUtils.copyNonNullProperties(comment, existedComment);
        existedComment.setUser(user);
        existedComment.setPost(post);
        return commentRepository.save(existedComment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
