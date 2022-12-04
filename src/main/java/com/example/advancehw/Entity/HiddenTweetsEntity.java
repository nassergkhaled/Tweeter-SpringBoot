package com.example.advancehw.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "hiddentweets")
@Data

public class HiddenTweetsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public int userid;
    public int tweetid;


}
