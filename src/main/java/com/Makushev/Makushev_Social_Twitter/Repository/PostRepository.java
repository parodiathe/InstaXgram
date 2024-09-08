package com.Makushev.Makushev_Social_Twitter.Repository;

import com.Makushev.Makushev_Social_Twitter.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.user.id=:userId")
        List<Post> findPostByUserId(Long userId);

}
