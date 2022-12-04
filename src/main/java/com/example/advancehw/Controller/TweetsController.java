package com.example.advancehw.Controller;

import com.example.advancehw.Dao.TweetsDao;
import com.example.advancehw.Dao.UsersDao;
import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.bodies.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tweets")
public class TweetsController {
    @Autowired
    private TweetsDao tweetDao;

    @PostMapping(path = "/")
    public String addNewTweet(@RequestBody TweetsEntity tweet)
    {
        return this.tweetDao.addNewTweet(tweet);
    }


    @GetMapping(path = "/view")
    public List<TweetsEntity>viewAll()
    {
        return this.tweetDao.showAll();
    }
}
