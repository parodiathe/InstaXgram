package com.Makushev.Makushev_Social_Twitter.Repository;

import com.Makushev.Makushev_Social_Twitter.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

