package com.Makushev.Makushev_Social_Twitter.service;

import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);

    public Chat findChatById(Long chatId) throws Exception;

    public List<Chat> findUsersChat (Long userId);

}
