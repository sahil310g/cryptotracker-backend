package com.crypto.tracker.model;

import lombok.Getter;

@Getter
public class RedditPost {
    RedditPostData data;

    @Getter
    public class RedditPostData{
        String selftext;
        String title;
    }

}
