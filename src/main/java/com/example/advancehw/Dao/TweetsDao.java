package com.example.advancehw.Dao;

import com.example.advancehw.Entity.*;
import com.example.advancehw.Repository.HiddenTweetsRepository;
import com.example.advancehw.Repository.SavedTweetsRepository;
import com.example.advancehw.Repository.TweetsRepository;
import com.example.advancehw.Repository.UsersRepository;
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
    @Autowired
    private SavedTweetsRepository savedTweetsRepository;
    @Autowired
    private HiddenTweetsRepository hiddenTweetsRepository;

    public List<TweetsEntity> showAll()
    {
        return this.tweetRepository.findAll();
    }

    public String addNewTweetAndUpdateTweet(TweetsEntity tweet)
    {
        try {
            this.tweetRepository.save(tweet);
            return "Done Successfully";
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }

    public String deleteTweet(Integer id) {
        try {
            Optional<TweetsEntity> tweetToDelete = Optional.ofNullable(this.tweetRepository.findAllById(id));
            if (tweetToDelete.isEmpty()) {
                return "No Tweet With This Id";
            } else {
                this.tweetRepository.delete(tweetToDelete.get());
                return "Success";
            }
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }

    public String saveTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToSave = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToSave.isPresent()) {
                    Optional<SavedTweetsEntity> savedTweet = Optional.ofNullable(this.savedTweetsRepository.findAllByUseridAndTweetid(userId,tweetId));
                    if(savedTweet.isEmpty()) {
                        SavedTweetsEntity entity = new SavedTweetsEntity();
                        entity.setTweetid(tweetId);
                        entity.setUserid(userId);
                        this.savedTweetsRepository.save(entity);
                        return "Succeed";
                    }
                    else
                    {
                        return "Already Saved";
                    }
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }


    public String unSaveTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweet.isPresent()) {
                    Optional<SavedTweetsEntity> tweetToUnSave=  Optional.ofNullable(this.savedTweetsRepository.findAllByUseridAndTweetid(userId,tweetId));
                    if(tweetToUnSave.isEmpty()){
                        return "You're Not Saving The Tweet In The First Place";}
                    int id=tweetToUnSave.get().getId();
                    this.savedTweetsRepository.deleteById(id);
                    return "Success";
                }else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        }
        catch (Exception e)
        {
            return e.getMessage();
            //return "Failed";
        }
    }

    public String hideTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToSave = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToSave.isPresent()) {
                    Optional<HiddenTweetsEntity> hiddenTweet = Optional.ofNullable(this.hiddenTweetsRepository.findAllByUseridAndTweetid(userId,tweetId));
                    if(hiddenTweet.isEmpty()) {
                    HiddenTweetsEntity entity = new HiddenTweetsEntity();
                    entity.setTweetid(tweetId);
                    entity.setUserid(userId);
                    this.hiddenTweetsRepository.save(entity);
                    return "Succeed";
                }
                else
                {
                    return "Already Hidden";
                }
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        }
        catch (Exception e)
        {
            return "Failed";
        }
    }

    public String unHideTweet(Integer tweetId, Integer userId) {
            try {
                Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
                if (user.isPresent()) {
                    Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                    if (tweet.isPresent()) {
                        Optional<HiddenTweetsEntity> tweetToUnHide=  Optional.ofNullable(this.hiddenTweetsRepository.findAllByUseridAndTweetid(userId,tweetId));
                        if(tweetToUnHide.isEmpty()){
                            return "You're Not Hiding The Tweet In The First Place";}
                        int id=tweetToUnHide.get().getId();
                        this.hiddenTweetsRepository.deleteById(id);
                        return "Success";
                    }else {
                        return "No Tweet With The Given Id";
                    }
                } else {
                    return "No User With The Given Id";
                }
            }
            catch (Exception e)
            {
                return e.getMessage();
                //return "Failed";
            }
    }
}
