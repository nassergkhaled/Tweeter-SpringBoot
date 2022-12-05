package com.example.advancehw.Controller;

import com.example.advancehw.Dao.TweetsDao;
import com.example.advancehw.Dao.UsersDao;
import com.example.advancehw.Entity.CommentsEntity;
import com.example.advancehw.Entity.LikesEntity;
import com.example.advancehw.Entity.TweetsEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "tweets")
public class TweetsController {
    @Autowired
    private TweetsDao tweetDao;
    @Autowired
    private UsersDao userDao;

    @PutMapping(path = "/")
    public String addNewTweet(@RequestBody TweetsEntity tweet)
    {
        return this.tweetDao.addNewTweetAndUpdateTweet(tweet);
    }

    @GetMapping(path = "/view/{idOfUser}")
    public List<TweetsEntity>viewAllUserTweets(@PathVariable ("idOfUser") Integer id)
    {
        return this.userDao.viewAllTweetsForUser(id);
    }

    @GetMapping(path = "/view")
    public List<TweetsEntity>viewAll()
    {
        return this.tweetDao.showAll();
    }

    @DeleteMapping("delete/{idOfTweet}")
    public String deleteTweet(@PathVariable("idOfTweet") Integer id) {
        return this.tweetDao.deleteTweet(id);
    }

    @GetMapping(path = "/save/{tweetId}/{userId}")
    public String saveTweet (@PathVariable ("tweetId")Integer tweetId,
                             @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.saveTweet(tweetId,userId);
    }

    @DeleteMapping(path = "/unsave/{tweetId}/{userId}")
    public String unSaveTweet (@PathVariable ("tweetId")Integer tweetId,
                             @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.unSaveTweet(tweetId,userId);
    }

    @GetMapping(path = "/save/view/{userid}")
    public List<TweetsEntity> viewAllSavedTweets(@PathVariable("userid") Integer userId)
    {
        return this.tweetDao.viewAllSavedTweets(userId);
    }

    @GetMapping(path = "/hide/{tweetId}/{userId}")
    public String hideTweet (@PathVariable ("tweetId")Integer tweetId,
                             @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.hideTweet(tweetId,userId);
    }

    @DeleteMapping(path = "/unhide/{tweetId}/{userId}")
    public String unHideTweet (@PathVariable ("tweetId")Integer tweetId,
                               @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.unHideTweet(tweetId,userId);
    }

    @GetMapping(path = "/like/{tweetId}/{userId}")
    public String likeTweet (@PathVariable ("tweetId")Integer tweetId,
                             @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.LikeTweet(tweetId,userId);
    }

    @DeleteMapping(path = "/unlike/{tweetId}/{userId}")
    public String unLikeTweet (@PathVariable ("tweetId")Integer tweetId,
                               @PathVariable ("userId")Integer userId)
    {
        return this.tweetDao.unLikeTweet(tweetId,userId);
    }

    @GetMapping(path = "/comment")
    public String commentOnTweet(@RequestBody CommentsEntity comment) throws ParseException {
        return this.tweetDao.commentOnATweet(comment);
    }

    @GetMapping(path = "/comments/view/{tweetid}")
    public List<CommentsEntity> viewTweetComments(@PathVariable ("tweetid") Integer tweetId)
    {
        return this.tweetDao.viewTweetComments(tweetId);
    }

    @DeleteMapping(path = "/comments/delete/{commentid}")
    public String deleteCommentOfATweet(@PathVariable ("commentid") Integer CommentId)
    {
        return this.tweetDao.deleteCommentOfATweet(CommentId);
    }

    @GetMapping(path = "/likes/view/{tweetid}")
    public List<LikesEntity> viewLikesOfTweet(@PathVariable ("tweetid") Integer tweetId)
    {
        return this.tweetDao.viewLikesOfTweet(tweetId);
    }

    @GetMapping(path = "/likes/count/{tweetid}")
    public String countLikesOfTweet(@PathVariable ("tweetid") Integer tweetId)
    {
        return this.tweetDao.countLikesOfTweet(tweetId).toString();
    }
}
