package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Alignment for composed screenshots.
 */
public enum Alignment {
    TOP("top"),
    CENTER("center"),
    BOTTOM("bottom");

    private final String value;

    Alignment(String value) {
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
