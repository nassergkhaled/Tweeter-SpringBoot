package com.example.advancehw.Repository;

import com.example.advancehw.Entity.HiddenTweetsEntity;
import com.example.advancehw.Entity.LikesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<LikesEntity, Integer> {


    public LikesEntity findAllByUseridAndTweetid(Integer userId, Integer tweetId);
    public List<LikesEntity> findAllByTweetid(Integer tweetId);
}
