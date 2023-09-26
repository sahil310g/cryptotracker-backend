package com.crypto.tracker.client;

import com.crypto.tracker.model.AccessTokenResponse;
import com.crypto.tracker.model.RedditPost;
import com.crypto.tracker.model.RedditResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Component
public class RedditClient {

//    private String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjk1NjUxNDM0LjUxMjgyOSwiaWF0IjoxNjk1NTY1MDM0LjUxMjgyOSwianRpIjoiSHA5MWR6Vm1CVnE2QjVEUWlDOVFxWDFaZkFpZ2t3IiwiY2lkIjoiQTd0azY3OGMzYTVlYmp3UVIzWlNaUSIsImxpZCI6InQyXzl3NXgxbHN2IiwiYWlkIjoidDJfOXc1eDFsc3YiLCJsY2EiOjE2MTEwNjQ4MDQwMDAsInNjcCI6ImVKeUtWdEpTaWdVRUFBRF9fd056QVNjIiwiZmxvIjo5fQ.ES8H2V-rfK6CwqfZqGfaYkHhQI7-8QcudOOlggKpOKWvUl-8hXlfnSkfwZgAYHsGcWFihMCkF8KzZhrlIoZN24w81SzlMwIwaYq8KZbpUHyQcoF57y6Q8VUy-Gz1ImvF6_42C-FFARiNuRhqV7kzljqxgjzNAxHtqgCUQzb62nF_zZHc-qWYkMrkUvmxw4HcRS996CH_XCyGJMZLPf9Lrs6iBJQGAGiOneCg7UBnLZ1btfxmhnq1fqfOI35R60bB76_cW84LWcQUeifluTN78WoaMM52M2ig0PBcj8Vks7GtQdK1DYJZBF4UVVjbMgh_WNCj8HgWkVE0rbewopE-9Q";

    public String refreshToken(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.reddit.com/api/v1/access_token";

        HttpHeaders headers = new HttpHeaders();
        String credentials = "A7tk678c3a5ebjwQR3ZSZQ:4Wmo9ere76mYMMXCmwJEJmwV1edHfg";
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + base64Credentials);
        headers.set("User-Agent","PostmanRuntime/7.29.2");

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "refresh_token");
        requestBody.add("refresh_token", "775292344735-6vr2iouySQ316V5eR8nJWyCZi2aeaA");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<AccessTokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, request, AccessTokenResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            AccessTokenResponse accessTokenResponse = response.getBody();
            return accessTokenResponse.getAccessToken();
        } else {
            throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
        }
    }

    public List<RedditPost> fetchRedditPosts(String query, Integer limit){

        String token = refreshToken();


        RestTemplate template = new RestTemplate();
        String url = "https://oauth.reddit.com/r/all/search?q="+ query +"&limit="+ limit.toString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        headers.add("User-Agent", "ChangeMeClient/0.1 by YourUsername");

        // Create the request entity with headers
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        // Make a GET request with the headers
        ResponseEntity<RedditResponse> rsp = template.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                RedditResponse.class
        );

        if (rsp.getStatusCode().is2xxSuccessful()) {
            RedditResponse response = rsp.getBody();
            return response.getData().getChildren();
        } else {
            // Handle errors
            throw new RuntimeException("API request failed with status code: " + rsp.getStatusCode());
        }
    }
}
