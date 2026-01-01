package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Level of ad and tracker blocking.
 */
public enum BlockLevel {
    NONE("none"),
    LIGHT("light"),
    NORMAL("normal"),
    PRO("pro"),
    PRO_PLUS("pro_plus"),
    ULTIMATE("ultimate");

    private final String value;

    BlockLevel(String value) {
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
