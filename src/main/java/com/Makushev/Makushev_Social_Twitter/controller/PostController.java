package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.models.Post;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.response.ApiResponse;
import com.Makushev.Makushev_Social_Twitter.service.PostService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Post post) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        Post createdPost = postService.createNewPost(post, reqUser.getId());

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@RequestHeader("Authorization") String jwt,
                                                  @PathVariable Long postId) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        String message = postService.deletePost(postId, reqUser.getId());
        ApiResponse res = new ApiResponse(message, true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
    } // good

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Long postId) throws Exception {
        Post post = postService.findPostById(postId);

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    } // good

    @GetMapping("/api/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Long userId){
        List<Post> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK); // good
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost(){

        List<Post> posts = postService.findAllPost();

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK); // good
    }

    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Long postId,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        Post post = postService.savedPost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED); // good
    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Long postId,
                                                @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        Post post = postService.likePost(postId, reqUser.getId());

        return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
    }






    /**
     * AutoWired
     */

    PostService postService;

    UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }
}


/**
 * SVOdka
 *
 * Добавление @RequestHeader("Authorization") String jwt,
 * и
 * User reqUser = userService.findUserByJwt(jwt)
 *
 * позволяет обеспечивать доступ к этой функции только через токен,
 * т.е только владельцу аккаунта
 *
 */


