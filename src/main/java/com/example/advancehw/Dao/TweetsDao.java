package com.example.advancehw.Dao;

import com.example.advancehw.Entity.*;
import com.example.advancehw.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    public List<TweetsEntity> showAll() {
        return this.tweetRepository.findAll();
    }

    public String addNewTweetAndUpdateTweet(TweetsEntity tweet) {
        try {
            this.tweetRepository.save(tweet);
            return "Done Successfully";
        } catch (Exception e) {
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
        } catch (Exception e) {
            return "Failed";
        }
    }

    public String saveTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToSave = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToSave.isPresent()) {
                    Optional<SavedTweetsEntity> savedTweet = Optional.ofNullable(this.savedTweetsRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (savedTweet.isEmpty()) {
                        SavedTweetsEntity entity = new SavedTweetsEntity();
                        entity.setTweetid(tweetId);
                        entity.setUserid(userId);
                        this.savedTweetsRepository.save(entity);
                        return "Succeed";
                    } else {
                        return "Already Saved";
                    }
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }


    public String unSaveTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweet.isPresent()) {
                    Optional<SavedTweetsEntity> tweetToUnSave = Optional.ofNullable(this.savedTweetsRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (tweetToUnSave.isEmpty()) {
                        return "You're Not Saving The Tweet In The First Place";
                    }
                    int id = tweetToUnSave.get().getId();
                    this.savedTweetsRepository.deleteById(id);
                    return "Success";
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return e.getMessage();
            //return "Failed";
        }
    }

    public String hideTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToHide = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToHide.isPresent()) {
                    Optional<HiddenTweetsEntity> hiddenTweet = Optional.ofNullable(this.hiddenTweetsRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (hiddenTweet.isEmpty()) {
                        HiddenTweetsEntity entity = new HiddenTweetsEntity();
                        entity.setTweetid(tweetId);
                        entity.setUserid(userId);
                        this.hiddenTweetsRepository.save(entity);
                        return "Succeed";
                    } else {
                        return "Already Hidden";
                    }
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public String unHideTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweet.isPresent()) {
                    Optional<HiddenTweetsEntity> tweetToUnHide = Optional.ofNullable(this.hiddenTweetsRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (tweetToUnHide.isEmpty()) {
                        return "You're Not Hiding The Tweet In The First Place";
                    }
                    int id = tweetToUnHide.get().getId();
                    this.hiddenTweetsRepository.deleteById(id);
                    return "Success";
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public String LikeTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToLike = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToLike.isPresent()) {
                    Optional<LikesEntity> likedTweet = Optional.ofNullable(this.likesRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (likedTweet.isEmpty()) {
                        LikesEntity entity = new LikesEntity();
                        entity.setTweetid(tweetId);
                        entity.setUserid(userId);
                        this.likesRepository.save(entity);
                        return "Succeed";
                    } else {
                        return "Already Liked";
                    }
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public String unLikeTweet(Integer tweetId, Integer userId) {
        try {
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweet.isPresent()) {
                    Optional<LikesEntity> tweetToUnLike = Optional.ofNullable(this.likesRepository.findAllByUseridAndTweetid(userId, tweetId));
                    if (tweetToUnLike.isEmpty()) {
                        return "You're Not Liking The Tweet In The First Place";
                    }
                    int id = tweetToUnLike.get().getId();
                    this.likesRepository.deleteById(id);
                    return "Success";
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public String commentOnATweet(CommentsEntity comment) {
        try {
            Integer tweetId=comment.getTweetid();
            Integer userId =comment.getUserid();
            Optional<UsersEntity> user = Optional.ofNullable(this.userRepository.findAllById(userId));
            if (user.isPresent()) {
                Optional<TweetsEntity> tweetToComment = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToComment.isPresent()) {
                    String description=comment.getDescription();
                    Date date=comment.getDate();
                    CommentsEntity entity =new CommentsEntity();
                    entity.setDate(date);
                    entity.setDescription(description);
                    entity.setUserid(userId);
                    entity.setTweetid(tweetId);
                    this.commentsRepository.save(entity);
                    return "Success";
                } else {
                    return "No Tweet With The Given Id";
                }
            } else {
                return "No User With The Given Id";
            }
        } catch (Exception e) {
            return "Failed";
        }
    }

    public List<CommentsEntity> viewTweetComments(Integer tweetId) {
        try {
            Optional<TweetsEntity> tweet = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
            if(tweet.isPresent())
            {
                List<CommentsEntity>tweetComments=this.commentsRepository.findAllByTweetid(tweetId);
                return tweetComments;
            }
            else
            {
                List<CommentsEntity>emptyComment=new ArrayList<>();
                return emptyComment;
            }
        }
        catch (Exception e)
        {
            List<CommentsEntity>emptyComment=new ArrayList<>();
            return emptyComment;
        }
    }

    public String deleteCommentOfATweet(Integer commentId) {
        try {
            Optional<CommentsEntity>commentToDelete=Optional.ofNullable(this.commentsRepository.findAllById(commentId));
            if(commentToDelete.isPresent())
            {
                this.commentsRepository.deleteById(commentId);
                return "Success";
            }
            else
            {
                return "No Comment With The Given Id";
            }
        }
        catch (Exception e)
        {
         return "Failed";
        }

    }

    public List<LikesEntity> viewLikesOfTweet(Integer tweetId) {
        try {
                Optional<TweetsEntity> tweetToViewLikesOf = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
                if (tweetToViewLikesOf.isPresent()) {
                    List<LikesEntity>tweetLikes=this.likesRepository.findAllByTweetid(tweetId);
                    return tweetLikes;
                }
                else
                {
                    List<LikesEntity>emptyLike=new ArrayList<>();
                    return emptyLike;
                }
        }
        catch (Exception e)
        {
            List<LikesEntity>emptyLike=new ArrayList<>();
            return emptyLike;
        }
    }

    public Integer countLikesOfTweet(Integer tweetId) {
        try {
        Optional<TweetsEntity> tweetToCountLikesOf = Optional.ofNullable(this.tweetRepository.findAllById(tweetId));
        if (tweetToCountLikesOf.isPresent()) {
            List<LikesEntity>tweetLikes=tweetToCountLikesOf.get().getTweetLikes();
            return tweetLikes.size();
        }
        else
        {
            return -2;
        }

    }
        catch (Exception e)
        {
            return -1;
        }
    }

    public List<TweetsEntity> viewAllSavedTweets(Integer userId) {
        try {
        Optional<UsersEntity>user=Optional.ofNullable(this.userRepository.findAllById(userId));
        if(user.isPresent())
        {
            List<SavedTweetsEntity> userSavedTweets=user.get().getSavedTweets();
            int counter=userSavedTweets.size();
            List<TweetsEntity>response = new ArrayList<>();
            while (counter!=0)
            {
                Optional<TweetsEntity>tweet = Optional.ofNullable(this.tweetRepository.findAllById(userSavedTweets.get(counter-1).getTweetid()));
                response.add(tweet.get());
                counter--;
            }
            return response;
        }
        else
        {

            System.out.println("User Not Found");
            List<TweetsEntity> EmptySavedTweetsList=new ArrayList<>();
            return EmptySavedTweetsList;
        }
    }
        catch (Exception e )
        {
            System.out.println(e.getMessage());
            List<TweetsEntity> EmptySavedTweetsList=new ArrayList<>();
            return EmptySavedTweetsList;
        }
    }
}