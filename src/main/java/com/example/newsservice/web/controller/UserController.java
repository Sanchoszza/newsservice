package com.example.newsservice.web.controller;

import com.example.newsservice.mapper.UserMapper;
import com.example.newsservice.model.User;
import com.example.newsservice.service.UserService;
import com.example.newsservice.web.model.user.UpsertUserRequest;
import com.example.newsservice.web.model.user.UserListResponse;
import com.example.newsservice.web.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService dbUserService;

    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(){
        return ResponseEntity.ok(userMapper.userListToUserResponseList(
                dbUserService.findAll()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(userMapper.userToResponse(
                dbUserService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request){

        User newUser = dbUserService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId,
                                               @RequestBody @Valid UpsertUserRequest request){
        User updateUser = dbUserService.update(userMapper.requestToUser(userId, request));

        return ResponseEntity.ok(userMapper.userToResponse(updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        dbUserService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
