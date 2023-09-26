package com.crypto.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendService {

    @Autowired
    private RedditService redditService;

    @Autowired
    private ChatGPTService chatGPTService;

    public Float analysePost(String query, Integer limit){

        String posts = redditService.redditPostGenerator(query, limit);

        List<String> allPosts =  chatGPTService.responseEvaluation(posts);

        Float score = (float) 0;

        for(String post:allPosts){
            if(post.equals("negative")) score-=1;
            else if(post.equals("positive")) score+=1;
        }

        return score/5;
    }

}
