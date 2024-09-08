package com.Makushev.Makushev_Social_Twitter.service.impl;

import com.Makushev.Makushev_Social_Twitter.Repository.ChatRepository;
import com.Makushev.Makushev_Social_Twitter.Repository.MessageRepository;
import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.Message;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.ChatService;
import com.Makushev.Makushev_Social_Twitter.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public Message createMessage(User user, Long chatId, Message req) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();

        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage; // good
    }

    @Override
    public List<Message> findChatsMessages(Long chatId) throws Exception {

        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);

    } // good


    /**
     * AutoWired
     */

    private MessageRepository messageRepository;
    private ChatService chatService;
    private ChatRepository chatRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatService chatService, ChatRepository chatRepository) {
        this.messageRepository = messageRepository;
        this.chatService = chatService;
        this.chatRepository = chatRepository;
    }
}
