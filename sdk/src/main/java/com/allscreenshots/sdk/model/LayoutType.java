package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Layout type for composed screenshots.
 */
public enum LayoutType {
    GRID("GRID"),
    HORIZONTAL("HORIZONTAL"),
    VERTICAL("VERTICAL"),
    MASONRY("MASONRY"),
    MONDRIAN("MONDRIAN"),
    PARTITIONING("PARTITIONING"),
    AUTO("AUTO");

    private final String value;

    LayoutType(String value) {
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
