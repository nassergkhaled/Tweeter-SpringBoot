package com.example.advancehw.bodies;

import lombok.Data;

import java.util.Date;
@Data
public class Tweet {
    public int userId;
    public Date date;
    public String description;
    public String hashtag;
}
