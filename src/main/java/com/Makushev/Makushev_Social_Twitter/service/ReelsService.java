package com.Makushev.Makushev_Social_Twitter.service;

import com.Makushev.Makushev_Social_Twitter.models.Reels;
import com.Makushev.Makushev_Social_Twitter.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Long userId) throws Exception;


}
