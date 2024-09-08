package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.models.Chat;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.request.CreateChatRequest;
import com.Makushev.Makushev_Social_Twitter.service.ChatService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest req) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        User user2 = userService.findUserById(req.getUserId());

        Chat chat = chatService.createChat(reqUser, user2);
        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findusersChat(@RequestHeader("Authorization") String jwt) {

        User user = userService.findUserByJwt(jwt);

        List <Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }








    /**
     * AutoWired
     */

    private ChatService chatService;
    private UserService userService;

    @Autowired
    public ChatController(ChatService chatService, UserService userService) {
        this.chatService = chatService;
        this.userService = userService;
    }
}
