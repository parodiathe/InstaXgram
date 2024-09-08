package com.Makushev.Makushev_Social_Twitter.service.impl;

import com.Makushev.Makushev_Social_Twitter.Repository.ReelsRepository;
import com.Makushev.Makushev_Social_Twitter.models.Reels;
import com.Makushev.Makushev_Social_Twitter.models.User;
import com.Makushev.Makushev_Social_Twitter.service.ReelsService;
import com.Makushev.Makushev_Social_Twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Override
    public Reels createReel(Reels reel, User user) {

        Reels createReel = new Reels();

        createReel.setTittle(reel.getTittle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);

    }

    @Override
    public List<Reels> findAllReels() {

        return reelsRepository.findAll();

    }

    @Override
    public List<Reels> findUsersReel(Long userId) throws Exception {

        userService.findUserById(userId);

        return  reelsRepository.findByUserId(userId);
    }


    /**
     * AutoWired
     */

    private ReelsRepository reelsRepository;
    private UserService userService;

    @Autowired
    public ReelsServiceImpl(ReelsRepository reelsRepository, UserService userService) {
        this.reelsRepository = reelsRepository;
        this.userService = userService;
    }
}
