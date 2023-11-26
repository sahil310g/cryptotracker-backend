package com.crypto.tracker.service;

import com.crypto.tracker.client.ChatGPTClient;
import com.crypto.tracker.client.SentimentClient;
import com.crypto.tracker.model.SentimentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SentimentService {

    @Autowired
    private SentimentClient sentimentClient;

    public List<Float> responseEvaluation(List<String> posts) {
        SentimentResponse sentimentResponse = sentimentClient.getSentimentAnalysis(posts);
        List<Float> scores = new ArrayList<>();
        for(String post:sentimentResponse.getProbability()){
            Float postValue = Float.parseFloat(post);
            scores.add(postValue);
        }
        return scores;
    }
}
