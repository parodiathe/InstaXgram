package com.Makushev.Makushev_Social_Twitter.service.impl;

import com.Makushev.Makushev_Social_Twitter.Repository.StoryRepository;
import com.Makushev.Makushev_Social_Twitter.models.Story;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.StoryService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {

    @Override
    public Story createStory(Story story, User user) {

        Story createdStory = new Story();

        createdStory.setCaption(story.getCaption());
        createdStory.setImage(story.getImage());
        createdStory.setUser(user);
        createdStory.setTimestamp(LocalDateTime.now());

        return storyRepository.save(createdStory);
    }

    @Override
    public List<Story> findStoryByUserId(Long userId) throws Exception {

        User user = userService.findUserById(userId);

        return storyRepository.findByUserId(userId);
    }






    /**
     * AutoWired
     */

    private StoryRepository storyRepository;
    private UserService userService;

    @Autowired
    public StoryServiceImpl(StoryRepository storyRepository, UserService userService) {
        this.storyRepository = storyRepository;
        this.userService = userService;
    }
}
