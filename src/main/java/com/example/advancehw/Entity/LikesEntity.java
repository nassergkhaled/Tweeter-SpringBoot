package com.example.advancehw.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tweetid",insertable = false,updatable = false)
    @JsonBackReference
    private TweetsEntity tweet;

}
