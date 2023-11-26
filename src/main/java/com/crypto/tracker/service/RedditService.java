package com.crypto.tracker.service;

import com.crypto.tracker.client.RedditClient;
import com.crypto.tracker.model.RedditPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedditService {

    @Autowired
    private RedditClient redditClient;

    public List<String> redditPostGenerator(String query, Integer limit) {

        List<RedditPost> redditPosts = redditClient.fetchRedditPosts(query, limit);
        List<String> allPosts= new ArrayList<>();
        for(RedditPost post: redditPosts){
            allPosts.add(post.getData().getTitle());
        }
        return allPosts;
    }
}
