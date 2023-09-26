package com.crypto.tracker.model;

import lombok.Getter;

@Getter
public class ChatGPTResponseData {

    ChatGPTAnalysis message;

    @Getter
    public class ChatGPTAnalysis{
        String role;
        String content;
    }

}
