package com.example.advancehw.Dao;

import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.Repository.TweetsRepository;
import com.example.advancehw.Repository.UsersRepository;
import com.example.advancehw.bodies.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetsDao {
    @Autowired
    private TweetsRepository tweetRepository;
    @Autowired
    private UsersRepository userRepository;

    public List<TweetsEntity> showAll()
    {
        return this.tweetRepository.findAll();
    }

    public String addNewTweet(TweetsEntity tweet)
    {
        try {
            this.tweetRepository.save(tweet);
            return "Tweeted Successfully";
        }
        catch (Exception e)
        {
            return "Failed";
        }

    }



}
