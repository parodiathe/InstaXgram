package com.Makushev.Makushev_Social_Twitter.service;

import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.Message;
import com.Makushev.Makushev_Social_Twitter.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Long chatId, Message req) throws Exception;

    public List<Message> findChatsMessages(Long chatId) throws Exception;

}
