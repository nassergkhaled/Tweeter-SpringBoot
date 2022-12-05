package com.example.advancehw.Repository;

import com.example.advancehw.Entity.CommentsEntity;
import com.example.advancehw.Entity.FollowersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Integer> {
    public List<CommentsEntity>findAllByTweetid(Integer id);

    public CommentsEntity findAllById(Integer commentId);
}
