package com.Makushev.Makushev_Social_Twitter.controller;

import com.Makushev.Makushev_Social_Twitter.models.Story;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.StoryService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){

        User reqUser = userService.findUserByJwt(jwt);

        Story createdStory = storyService.createStory(story, reqUser);

        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Long userId,  @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);

        List<Story> stories = storyService.findStoryByUserId(userId);

        return stories;
    }



    /**
     * AutoWired
     */


    private StoryService storyService;
    private UserService userService;

    @Autowired
    public StoryController(StoryService storyService, UserService userService) {
        this.storyService = storyService;
        this.userService = userService;
    }
}
