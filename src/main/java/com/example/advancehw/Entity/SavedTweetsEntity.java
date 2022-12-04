package com.example.advancehw.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "savedtweets")
@Data

public class SavedTweetsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public int userid;
    public int tweetid;


}
