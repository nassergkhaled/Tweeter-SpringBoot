package com.example.advancehw.Repository;

import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetsRepository extends JpaRepository<TweetsEntity, Integer> {
    public TweetsEntity findAllById(Integer id) ;


    public TweetsEntity findAllById(TweetsEntity tweetsEntity);
}
