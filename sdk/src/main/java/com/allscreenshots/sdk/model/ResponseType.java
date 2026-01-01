package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Response type for screenshot requests.
 */
public enum ResponseType {
    /**
     * Return the screenshot as binary image data.
     */
    BINARY("BINARY"),

    /**
     * Return the screenshot metadata as JSON.
     */
    JSON("JSON");

    private final String value;

    ResponseType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
