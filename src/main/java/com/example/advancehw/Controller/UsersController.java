package com.example.advancehw.Controller;

import com.example.advancehw.Dao.UsersDao;
import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.bodies.LogInBody;
import com.example.advancehw.bodies.ResetPasswordBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UsersController {
    @Autowired
    private UsersDao userDao;

    @GetMapping("/{idCode}")
    public List<TweetsEntity> viewAllTweets(@PathVariable("idCode") Integer id) {
        return this.userDao.viewAllTweetsForUser(id);
    }

    @GetMapping(path = "/view")
    public List<UsersEntity>viewAll()
    {
        return this.userDao.showAll();
    }
    @PostMapping(path = "/login")
    public String logIn(@RequestBody LogInBody body)
    {
        return this.userDao.logIn(body.getUserName(), body.getPassword());
    }
    @PostMapping(path = "/")
    public String signUp(@RequestBody UsersEntity user)
    {
        return this.userDao.signUp(user);
    }
    @PatchMapping(path = "/password")
    public String updatePassword(@RequestBody ResetPasswordBody body)
    {
        return this.userDao.resetPassword(body);
    }

    @GetMapping(path = "/view/{idOdUser}")
    public UsersEntity viewUserInfo(@PathVariable("idOdUser")Integer id)
    {
        return this.userDao.viewUserInfo(id);
    }
    @GetMapping(path = "follow/{followerId}/{followedId}")
    public String followUser( @PathVariable ("followerId") Integer followerId,
                              @PathVariable("followedId") Integer followedId
                            )
    {
        return this.userDao.followUser(followerId,followedId);
    }

    @DeleteMapping(path = "unfollow/{followerId}/{followedId}")
    public String unFollowUser( @PathVariable ("followerId") Integer followerId,
                              @PathVariable("followedId") Integer followedId
    )
    {
        return this.userDao.unFollowUser(followerId,followedId);
    }

    @GetMapping(path = "/followed/tweets/{userid}")
    public List<TweetsEntity> viewFollowedUsersTweets(@PathVariable("userid") Integer userId)
    {
        return this.userDao.viewFollowedUsersTweets(userId);
    }

}
