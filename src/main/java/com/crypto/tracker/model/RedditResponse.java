package com.crypto.tracker.model;

import lombok.Getter;

import java.util.List;
@Getter
public class RedditResponse {

    RedditResponseData data;
    String kind;

    @Getter
    public class RedditResponseData{
        List<RedditPost> children;
    }

}


