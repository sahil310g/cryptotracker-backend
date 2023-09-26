package com.crypto.tracker.service;

import com.crypto.tracker.client.ChatGPTClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChatGPTService {

    @Autowired
    private  ChatGPTClient chatGPTClient;
    public List<String> responseEvaluation(String posts){

        String response = chatGPTClient.getChatGPTResponse(posts).getContent();

        String[] allResponse = response.split("\n");

        List<String> responses= new ArrayList<>();

        for (String st: allResponse){
            responses.add(st.trim().toLowerCase());
        }


        List<String> resultList = new ArrayList<>();

        for (String str : responses) {
            String[] parts = str.split("\\. ");
            if (parts.length == 2) {
                String textPart = parts[1].trim();
                resultList.add(textPart);
            } else if(parts.length == 1){
                String textPart = parts[0].trim();
                resultList.add(textPart);
            }
        }

        return resultList;
    }
}
