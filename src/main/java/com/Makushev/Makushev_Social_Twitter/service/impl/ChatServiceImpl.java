package com.Makushev.Makushev_Social_Twitter.service.impl;

import com.Makushev.Makushev_Social_Twitter.Repository.ChatRepository;
import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.ChatService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Override
    public Chat createChat(User reqUser, User user2) {

        Chat isExist = chatRepository.findChatByUserId(user2, reqUser);

        if(isExist!=null) {
            return isExist;
        }

        Chat chat = new Chat();
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Long chatId) throws Exception {

        Optional<Chat> opt = chatRepository.findById(chatId);

        if(opt.isEmpty()) {
            throw new Exception("Chat with id - " + chatId + " not found");
        }

        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Long userId) {
        return chatRepository.findByUsersId(userId);
    }








    /**
     * AutoWired
     */

    private UserService userService;
    private ChatRepository chatRepository;

    @Autowired
    public ChatServiceImpl(UserService userService, ChatRepository chatRepository) {
        this.userService = userService;
        this.chatRepository = chatRepository;
    }
}
