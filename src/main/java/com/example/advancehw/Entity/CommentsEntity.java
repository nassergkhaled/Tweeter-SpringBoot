package com.example.advancehw.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
public class CommentsEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Date date;
    public String description;
    public int userid;
    public int tweetid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tweetid",insertable = false,updatable = false)
    @JsonBackReference
    private TweetsEntity tweet;


}
