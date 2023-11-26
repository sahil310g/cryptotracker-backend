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

    @Autowired
    private SentimentService sentimentService;

    public Float analysePost(String query, Integer limit){

        List<String> posts = redditService.redditPostGenerator(query, limit);

//        List<String> allPosts =  chatGPTService.responseEvaluation(posts);
        List<Float> probabilities = sentimentService.responseEvaluation(posts);

        float score = 0.0f;
        for (Float probability : probabilities){
            score = score + probability;
        }
        return score/5;
    }

}
