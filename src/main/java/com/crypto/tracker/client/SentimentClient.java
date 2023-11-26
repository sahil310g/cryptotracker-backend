package com.crypto.tracker.client;

import com.crypto.tracker.model.RedditResponse;
import com.crypto.tracker.model.SentimentRequest;
import com.crypto.tracker.model.SentimentResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SentimentClient {

    public SentimentResponse getSentimentAnalysis(List<String> texts) {

        RestTemplate template = new RestTemplate();
        String url = "https://sentiment-analysis-production.up.railway.app/predict";

        // Create the request entity with headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SentimentRequest request = new SentimentRequest(texts);
        HttpEntity<SentimentRequest> requestEntity = new HttpEntity<>(request, headers);

        // Make a GET request with the headers
        ResponseEntity<SentimentResponse> rsp = template.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                SentimentResponse.class
        );
        return rsp.getBody();
    }
}
