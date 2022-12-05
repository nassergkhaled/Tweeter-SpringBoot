package com.example.advancehw.Dao;

import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.Repository.FollowersRepository;
import com.example.advancehw.Repository.UsersRepository;
import com.example.advancehw.bodies.ResetPasswordBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersDao {
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private FollowersRepository followersRepository;

    public List<UsersEntity> showAll()
    {
        return this.userRepository.findAll();
    }
    public String logIn(String userName,String password)
    {
        try {
            Optional<UsersEntity> user;
            user = Optional.ofNullable(this.userRepository.findAllByUserNameAndPassword(userName, password));
            if (user.isPresent()) {
                return "Welcome " + user.get().getFullName();
            } else {
                return "Failed To Login";
            }
        }
        catch (Exception e)
        {
            return "Failed To Login";
        }
    }

    public String signUp(UsersEntity user) {
        try {
            if (this.userRepository.existsByUserName(user.getUserName())) {
                return "UserName Is Already In Use";
            }
            this.userRepository.save(user);
            return " Success";
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            return "Failed";


        }

    }

    public List<TweetsEntity> viewAllTweetsForUser(Integer id) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(id));
            if (user.isPresent()) {
                return user.get().getUserTweets();
            } else {
                List<TweetsEntity> emptyList = new ArrayList<>();
                return emptyList;
            }
        }
        catch(Exception e)
        {
            List<TweetsEntity> emptyList = new ArrayList<>();
            return emptyList;
        }
    }

    public UsersEntity viewUserInfo(Integer id) {
        try {
        Optional<UsersEntity>user=Optional.ofNullable(this.userRepository.findAllById(id));
        if(user.isPresent())
        {
            return user.get();
        }
        else
        {
            UsersEntity emptyUser =new UsersEntity();
            return emptyUser;
        }
    }
        catch (Exception e)
        {
            UsersEntity emptyUser =new UsersEntity();
            return emptyUser;
        }
    }

    public String resetPassword(ResetPasswordBody body) {
        try {
            Optional<UsersEntity> user=Optional.ofNullable(this.userRepository.findAllById(body.getUserId()));
            if(user.isPresent())
            {
                if(user.get().getPassword().equals(body.getNewPassword())){return "You Entered Your Old Password";}
                user.get().setPassword(body.getNewPassword());
                this.userRepository.save(user.get());
                return "Success";
            }
            else
            {
                return "No User With Entered Id";
            }
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }
    public String followUser(Integer followerId,Integer followedId)
    {
        try {
            Optional<UsersEntity> user1 = Optional.ofNullable(this.userRepository.findAllById(followedId));
            Optional<UsersEntity> user2 = Optional.ofNullable(this.userRepository.findAllById(followerId));
            if ((user1.isEmpty()) || (user2.isEmpty())) {
                return "Invalid User Id";
            } else {
                FollowersEntity entity = new FollowersEntity();
                entity.setFollowedId(followedId);
                entity.setFollowerId(followerId);
                this.followersRepository.save(entity);
                return "Success";
            }
        }
        catch (Exception e)
        {
            return "Failed";
        }

    }

    public String unFollowUser(Integer followerId, Integer followedId) {
        try {
            Optional<UsersEntity> user1 = Optional.ofNullable(this.userRepository.findAllById(followedId));
            Optional<UsersEntity> user2 = Optional.ofNullable(this.userRepository.findAllById(followerId));
            if ((user1.isEmpty()) || (user2.isEmpty())) {
                return "Invalid User Id";
            } else {
                Optional<FollowersEntity> followersEntity= Optional.ofNullable(this.followersRepository.findAllByFollowedIdAndAndFollowerId(followedId,followerId));
                if(followersEntity.isEmpty()){
                    return "You're Not Following Him In The First Place";}
                int id=followersEntity.get().getId();
                this.followersRepository.deleteById(id);
                return "Success";
            }
        }
        catch (Exception e)
        {

            return "Failed";
        }
    }
}
