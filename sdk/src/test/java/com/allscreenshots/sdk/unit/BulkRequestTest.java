package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BulkRequestTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void buildWithStringUrls() {
        BulkRequest request = BulkRequest.builder()
                .addUrl("https://example.com")
                .addUrl("https://github.com")
                .build();

        assertEquals(2, request.getUrls().size());
        assertEquals("https://example.com", request.getUrls().get(0).getUrl());
        assertEquals("https://github.com", request.getUrls().get(1).getUrl());
    }

    @Test
    void buildWithUrlRequests() {
        BulkRequest request = BulkRequest.builder()
                .addUrl(BulkUrlRequest.builder()
                        .url("https://example.com")
                        .options(BulkUrlOptions.builder()
                                .device("iPhone 14")
                                .fullPage(true)
                                .build())
                        .build())
                .build();

        assertEquals(1, request.getUrls().size());
        assertEquals("https://example.com", request.getUrls().get(0).getUrl());
        assertEquals("iPhone 14", request.getUrls().get(0).getOptions().getDevice());
    }

    @Test
    void buildWithDefaults() {
        BulkRequest request = BulkRequest.builder()
                .addUrl("https://example.com")
                .defaults(BulkDefaults.builder()
                        .device("Desktop HD")
                        .fullPage(true)
                        .format(ImageFormat.PNG)
                        .build())
                .build();

        assertNotNull(request.getDefaults());
        assertEquals("Desktop HD", request.getDefaults().getDevice());
        assertTrue(request.getDefaults().getFullPage());
        assertEquals(ImageFormat.PNG, request.getDefaults().getFormat());
    }

    @Test
    void requiresAtLeastOneUrl() {
        assertThrows(IllegalArgumentException.class, () ->
                BulkRequest.builder().build()
        );
    }

    @Test
    void limitedTo100Urls() {
        BulkRequest.Builder builder = BulkRequest.builder();
        for (int i = 0; i < 101; i++) {
            builder.addUrl("https://example.com/" + i);
        }

        assertThrows(IllegalArgumentException.class, builder::build);
    }

    @Test
    void serializesToJson() throws Exception {
        BulkRequest request = BulkRequest.builder()
                .addUrl("https://example.com")
                .defaults(BulkDefaults.builder()
                        .device("Desktop HD")
                        .build())
                .build();

        String json = objectMapper.writeValueAsString(request);

        assertTrue(json.contains("\"url\":\"https://example.com\""));
        assertTrue(json.contains("\"device\":\"Desktop HD\""));
    }
}
