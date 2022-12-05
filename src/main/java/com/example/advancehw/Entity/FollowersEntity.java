package com.example.advancehw.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "followers")
@Data

public class FollowersEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name="followedid", nullable = false, unique = true)
    public int followedId;
    @Column(name="followerid", nullable = false, unique = true)
    public int followerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "followerid",insertable = false,updatable = false)
    @JsonBackReference
    private UsersEntity user;


}
