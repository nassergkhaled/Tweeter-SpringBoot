package com.example.advancehw.Entity;

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


}
