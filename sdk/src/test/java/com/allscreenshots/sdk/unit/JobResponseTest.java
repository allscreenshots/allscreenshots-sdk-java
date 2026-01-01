package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.model.JobResponse;
import com.allscreenshots.sdk.model.JobStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JobResponseTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void deserializesFromJson() throws Exception {
        String json = """
                {
                    "id": "job-123",
                    "status": "COMPLETED",
                    "url": "https://example.com",
                    "resultUrl": "https://storage.example.com/result.png",
                    "createdAt": "2024-01-15T10:30:00Z"
                }
                """;

        JobResponse response = objectMapper.readValue(json, JobResponse.class);

        assertEquals("job-123", response.getId());
        assertEquals("COMPLETED", response.getStatus());
        assertEquals(JobStatus.COMPLETED, response.getStatusEnum());
        assertEquals("https://example.com", response.getUrl());
        assertEquals("https://storage.example.com/result.png", response.getResultUrl());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void statusHelperMethods() throws Exception {
        String completedJson = """
                {"id": "1", "status": "COMPLETED"}
                """;
        String failedJson = """
                {"id": "2", "status": "FAILED"}
                """;
        String processingJson = """
                {"id": "3", "status": "PROCESSING"}
                """;
        String queuedJson = """
                {"id": "4", "status": "QUEUED"}
                """;
        String cancelledJson = """
                {"id": "5", "status": "CANCELLED"}
                """;

        JobResponse completed = objectMapper.readValue(completedJson, JobResponse.class);
        JobResponse failed = objectMapper.readValue(failedJson, JobResponse.class);
        JobResponse processing = objectMapper.readValue(processingJson, JobResponse.class);
        JobResponse queued = objectMapper.readValue(queuedJson, JobResponse.class);
        JobResponse cancelled = objectMapper.readValue(cancelledJson, JobResponse.class);

        assertTrue(completed.isCompleted());
        assertTrue(completed.isTerminal());
        assertFalse(completed.isFailed());

        assertTrue(failed.isFailed());
        assertTrue(failed.isTerminal());
        assertFalse(failed.isCompleted());

        assertTrue(processing.isProcessing());
        assertFalse(processing.isTerminal());

        assertTrue(queued.isQueued());
        assertFalse(queued.isTerminal());

        assertTrue(cancelled.isCancelled());
        assertTrue(cancelled.isTerminal());
    }

    @Test
    void ignoresUnknownProperties() throws Exception {
        String json = """
                {
                    "id": "job-123",
                    "status": "COMPLETED",
                    "unknownField": "someValue"
                }
                """;

        JobResponse response = objectMapper.readValue(json, JobResponse.class);

        assertEquals("job-123", response.getId());
        assertEquals("COMPLETED", response.getStatus());
    }
}
