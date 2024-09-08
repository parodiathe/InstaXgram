package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.models.Message;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.MessageService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreateMessage {

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Long chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        Message message = messageService.createMessage(user, chatId, req);

        return message; // good
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage(@RequestHeader("Authorization") String jwt,
                                          @PathVariable Long chatId) throws Exception {

        User user = userService.findUserByJwt(jwt);

        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages; // good
    }








    /**
     * AutoWired
     */

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public CreateMessage(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }
}
