package com.Makushev.Makushev_Social_Twitter.service;

import com.Makushev.Makushev_Social_Twitter.models.Post;

import java.util.List;

public interface PostService {

    Post createNewPost(Post post, Long userId) throws Exception;

    String deletePost(Long postId, Long userId) throws Exception;

    List<Post> findPostByUserId(Long userId);

    Post findPostById(Long postId) throws Exception;

    List<Post> findAllPost();

    Post savedPost(Long postId, Long userId) throws Exception;

    Post likePost(Long postId, Long userId) throws Exception;


}
