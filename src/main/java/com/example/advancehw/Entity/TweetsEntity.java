package com.example.advancehw.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tweets")
@Data

public class TweetsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(name = "userid")
    public int userId;
    public Date date;
    public String description;
    public String hashtag;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userid",insertable = false,updatable = false)
    @JsonBackReference
    private UsersEntity user;

    @OneToMany(mappedBy = "tweet")
    @JsonManagedReference
    private List<CommentsEntity> tweetComments;

    @OneToMany(mappedBy = "tweet")
    @JsonManagedReference
    private List<LikesEntity> tweetLikes;
}
