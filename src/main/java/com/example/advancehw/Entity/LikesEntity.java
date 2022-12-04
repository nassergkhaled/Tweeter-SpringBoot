package com.example.advancehw.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Data

public class LikesEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public int userid;
    public int tweetid;


}
