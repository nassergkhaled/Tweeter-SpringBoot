package com.example.advancehw.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data

public class UsersEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Date birthday;
    @Column(name = "fullname")
    public String fullName;
    @Column(name = "username")
    public String userName;
    public String location;
    public String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<TweetsEntity> userTweets;
}
