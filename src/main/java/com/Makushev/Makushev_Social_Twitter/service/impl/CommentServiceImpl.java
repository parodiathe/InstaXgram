package com.Makushev.Makushev_Social_Twitter.service.impl;

import com.Makushev.Makushev_Social_Twitter.Repository.CommentRepository;
import com.Makushev.Makushev_Social_Twitter.Repository.PostRepository;
import com.Makushev.Makushev_Social_Twitter.models.Comment;
import com.Makushev.Makushev_Social_Twitter.models.Post;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.CommentService;
import com.Makushev.Makushev_Social_Twitter.service.PostService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {

        User user  = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCraetedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws Exception {

        Optional<Comment> opt = commentRepository.findById(commentId);

        if(opt.isEmpty()) {
            throw new Exception("Comment not exist");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Long CommentId, Long userId) throws Exception {

        Comment comment = findCommentById(CommentId);

        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)) {

            comment.getLiked().add(user);

        }

        else comment.getLiked().remove(user);

        return commentRepository.save(comment);

    }


    /**
     * AutoWired
     */


    private PostService postService;
    private UserService userService;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(PostService postService, UserService userService, CommentRepository commentRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }
}
