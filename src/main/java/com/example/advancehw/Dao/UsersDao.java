package com.example.advancehw.Dao;

import com.example.advancehw.Entity.TweetsEntity;
import com.example.advancehw.Entity.UsersEntity;
import com.example.advancehw.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersDao {
    @Autowired
    private UsersRepository userRepository;

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
                return "Failed To Login";
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
}
