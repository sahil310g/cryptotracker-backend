package com.crypto.tracker.service;

import com.crypto.tracker.client.RedditClient;
import com.crypto.tracker.model.RedditPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedditService {

    @Autowired
    private RedditClient redditClient;

    public String redditPostGenerator(String query, Integer limit) {

        List<RedditPost> redditPosts = redditClient.fetchRedditPosts(query, limit);

        StringBuilder allPosts= new StringBuilder();
        Integer counter=1;

        for(RedditPost post: redditPosts){
            allPosts.append(counter.toString()).append(". ");

            // Sentiment analysis based on title only
            allPosts.append(post.getData().getTitle()).append(" ");

//            if(!post.getData().getSelftext().isEmpty()) {
//                allPosts.append(post.getData().getSelftext());
//            }
//            else {
//                allPosts.append(post.getData().getTitle());
//            }

            counter++;
        }
        return allPosts.toString();
    }
}
