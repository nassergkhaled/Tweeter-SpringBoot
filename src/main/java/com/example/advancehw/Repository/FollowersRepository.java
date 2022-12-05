package com.example.advancehw.Repository;

import com.example.advancehw.Entity.FollowersEntity;
import com.example.advancehw.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface FollowersRepository extends JpaRepository<FollowersEntity, Integer> {

    public FollowersEntity findAllByFollowedIdAndAndFollowerId(Integer FollowedId, Integer FollowerId);


}
