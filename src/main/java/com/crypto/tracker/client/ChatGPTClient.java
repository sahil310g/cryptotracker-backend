package com.crypto.tracker.client;

import com.crypto.tracker.model.ChatGPTResponse;
import com.crypto.tracker.model.ChatGPTResponseData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatGPTClient {

    public ChatGPTResponseData.ChatGPTAnalysis getChatGPTResponse(String posts) {
        String token = "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";

        RestTemplate gptTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        headers.add("Content-Type", "application/json");

        // Create the request entity with headers and a request body
        String requestBody = "{\n" +
                             "    \"model\": \"gpt-3.5-turbo\",\n" +
                             "    \"messages\": [\n" +
                             "        {\n" +
                             "            \"role\": \"system\",\n" +
                             "            \"content\": \"You will be provided with a list of tweets, and your task is to classify their sentiment as positive, neutral, or negative.\"\n" +
                             "        },\n" +
                             "        {\n" +
                             "            \"role\": \"user\",\n" +
                             "            \"content\": \""+ posts + "\""+
                             "        }\n" +
                             "    ]\n" +
                             "}"; // Replace with your JSON request body
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make a POST request with the headers and body
        ResponseEntity<ChatGPTResponse> rsp = gptTemplate.exchange(
                url,
                HttpMethod.POST, // Change to the desired HTTP method (e.g., POST, PUT, etc.)
                requestEntity,
                ChatGPTResponse.class
        );

        if (rsp.getStatusCode().is2xxSuccessful()) {
            ChatGPTResponse chatGPTResponse = rsp.getBody();
            return chatGPTResponse.getChoices().get(0).getMessage();
        } else {
            // Handle errors
            throw new RuntimeException("API request failed with status code: " + rsp.getStatusCode());
        }

    }
}
