package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * API error response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private String code;

    @JsonProperty("details")
    private Object details;

    public ErrorResponse() {
    }

    public String getError() { return error; }
    public String getMessage() { return message; }
    public String getCode() { return code; }
    public Object getDetails() { return details; }
}
