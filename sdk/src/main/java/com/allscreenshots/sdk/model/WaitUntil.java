package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Specifies when to consider navigation succeeded.
 */
public enum WaitUntil {
    /**
     * Wait until the load event is fired.
     */
    LOAD("load"),

    /**
     * Wait until the DOMContentLoaded event is fired.
     */
    DOM_CONTENT_LOADED("domcontentloaded"),

    /**
     * Wait until there are no network connections for at least 500ms.
     */
    NETWORK_IDLE("networkidle"),

    /**
     * Wait until the first byte of the response is received.
     */
    COMMIT("commit");

    private final String value;

    WaitUntil(String value) {
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
