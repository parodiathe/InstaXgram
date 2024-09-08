package com.Makushev.Makushev_Social_Twitter.Repository;

import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    public List<Chat> findByUsersId(Long userId);

    @Query("select c from Chat c Where :user member of c.users And :reqUser Member of c.users")
    public Chat findChatByUserId(@Param("user") User user, @Param("reqUser") User reqUser);

}
