package com.example.advancehw.Repository;

import com.example.advancehw.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    public  UsersEntity findAllById(Integer id) ;
    public Optional<UsersEntity> findByUserNameAndPassword(String userName,String password);

    public boolean existsByUserName(String userName);

    public UsersEntity findAllByUserNameAndPassword(String userName, String password);
}
