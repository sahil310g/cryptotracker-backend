package com.crypto.tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AccessTokenResponse {
    @JsonProperty("access_token")
    String accessToken;

}
