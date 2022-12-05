package com.example.advancehw.Dao;

import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.Repository.FollowersRepository;
import com.example.advancehw.Repository.TweetsRepository;
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

    @Autowired
    private TweetsRepository tweetsRepository;
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
                Optional<UsersEntity> user2;
                user2 = Optional.ofNullable(this.userRepository.findAllByUserName(userName));
                if(user2.isPresent())
                {
                    return "Wrong Password";
                }

                return "No User With The Entered Username";
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
                //No User With Given Id
            }
        }
        catch(Exception e)
        {
            List<TweetsEntity> emptyList = new ArrayList<>();
            return emptyList;
            //Error
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
            //No User With Given Id
        }
    }
        catch (Exception e)
        {
            UsersEntity emptyUser =new UsersEntity();
            return emptyUser;
            //Error
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

            System.out.println(e.getMessage());
            return "Failed";
        }
    }

    public List<TweetsEntity> viewFollowedUsersTweets(Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if(user.isPresent())
            {
                if(user.get().getFollowedUsers().size()==0)
                {
                    //return "You're Not Following Any One";
                    return new ArrayList<>();
                }
                else
                {
                    List<FollowersEntity>entity=user.get().getFollowedUsers();
                    //System.out.println(entity.toString());
                    int counter=entity.size();
                    int outerCounter =entity.size();
                    //System.out.println(counter);
                    List<UsersEntity> followedUsers =new ArrayList<>();
                    while (counter!=0)
                    {
                        followedUsers.add(this.userRepository.findAllById(entity.get(counter-1).getFollowedId()));
                        counter--;
                    }
                    List<TweetsEntity>followedUsersTweets=new ArrayList<>();
                    while (outerCounter !=0)
                    {
                        int innerCounter= followedUsers.get(outerCounter-1).getUserTweets().size();
                        //System.out.println(innerCounter+"   "+outerCounter);
                        UsersEntity followedUser = followedUsers.get(outerCounter-1);
                        while (innerCounter!=0)
                        {
                            List<TweetsEntity>followedUserTweets = followedUser.getUserTweets();
                            followedUsersTweets.addAll(followedUserTweets);
                            //followedUsersTweets.add(this.tweetsRepository.findAllById(followedUsers.get(outerCounter-1).getUserTweets().get(innerCounter-1)));
                            innerCounter--;
                            //System.out.println("Finished");
                        }
                        outerCounter--;
                    }
                   // return followedUsers.toString();
                    //System.out.println(followedUsersTweets.toString());
                    return followedUsersTweets;
                }
            }
            else
            {
                //No User With Entered Id
                //return "No User With Entered Id";
                return new ArrayList<>();
            }
        }
        catch (Exception e)
        {


            System.out.println(e.getMessage()+"\n"+e.toString());
            //return "Error";
            return new ArrayList<>();
        }
    }
}
