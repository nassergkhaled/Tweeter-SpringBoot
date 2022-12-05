package com.example.advancehw.Repository;

import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.HiddenTweetsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiddenTweetsRepository extends JpaRepository<HiddenTweetsEntity, Integer> {


    public HiddenTweetsEntity findAllByUseridAndTweetid(Integer userId, Integer tweetId);
}
