package com.allscreenshots.sdk.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Status of an asynchronous screenshot job.
 */
public enum JobStatus {
    QUEUED("QUEUED"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    CANCELLED("CANCELLED");

    private final String value;

    JobStatus(String value) {
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

    public static JobStatus fromValue(String value) {
        for (JobStatus status : values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown job status: " + value);
    }
}
