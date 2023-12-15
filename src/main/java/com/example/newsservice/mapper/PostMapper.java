package com.example.newsservice.mapper;

import com.example.newsservice.model.Post;
import com.example.newsservice.web.model.post.PostListResponse;
import com.example.newsservice.web.model.post.PostResponse;
import com.example.newsservice.web.model.post.UpsertPostRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(PostMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post requestToPost(UpsertPostRequest request);

    @Mapping(source = "postId", target = "id")
    Post requestToPost(Long postId, UpsertPostRequest request);

    PostResponse postToResponse(Post post);

    List<PostResponse> postListToResponseList(List<Post> posts);

    default PostListResponse postListToPostListResponse(List<Post> posts){
        PostListResponse response = new PostListResponse();
        response.setPosts(postListToResponseList(posts));

        return response;
    }
}
