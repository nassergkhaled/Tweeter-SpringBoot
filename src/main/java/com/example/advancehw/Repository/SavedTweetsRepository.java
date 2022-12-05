package com.example.advancehw.Repository;

import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.SavedTweetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedTweetsRepository extends JpaRepository<SavedTweetsEntity, Integer> {


    public SavedTweetsEntity findAllByUseridAndTweetid(Integer userId,Integer TweetId);



}
