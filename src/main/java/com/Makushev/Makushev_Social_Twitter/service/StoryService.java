package com.Makushev.Makushev_Social_Twitter.service;

import com.Makushev.Makushev_Social_Twitter.models.Story;
import com.Makushev.Makushev_Social_Twitter.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory (Story story, User user);

    public List<Story> findStoryByUserId(Long userId) throws Exception;
}
