package com.example.advancehw.Controller;

import com.example.advancehw.Dao.UsersDao;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.bodies.LogInBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UsersController {
    @Autowired
    private UsersDao userDao;

//    @GetMapping("/{idCode}")
//    public List<TweetsEntity> viewAllTweets(@PathVariable("idCode") Integer id) {
//        return this.userDao.viewAllTweetsForUser(id);
//    }

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
    @PostMapping(path = "signup")
    public String signUp(@RequestBody UsersEntity user)
    {
        return this.userDao.signUp(user);
    }

}
