package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Output image format for screenshots.
 */
public enum ImageFormat {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
    WEBP("webp"),
    PDF("pdf");

    private final String value;

    ImageFormat(String value) {
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
