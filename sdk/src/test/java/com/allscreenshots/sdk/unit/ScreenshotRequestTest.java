package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScreenshotRequestTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void buildBasicRequest() {
        ScreenshotRequest request = ScreenshotRequest.builder()
                .url("https://example.com")
                .build();

        assertEquals("https://example.com", request.getUrl());
        assertNull(request.getDevice());
        assertNull(request.getFullPage());
    }

    @Test
    void buildFullRequest() {
        ScreenshotRequest request = ScreenshotRequest.builder()
                .url("https://example.com")
                .device("Desktop HD")
                .fullPage(true)
                .format(ImageFormat.PNG)
                .quality(90)
                .delay(1000)
                .waitFor("#content")
                .waitUntil(WaitUntil.NETWORK_IDLE)
                .timeout(30000)
                .darkMode(true)
                .customCss("body { background: white; }")
                .hideSelectors(List.of(".ad", ".banner"))
                .selector("#main")
                .blockAds(true)
                .blockCookieBanners(true)
                .blockLevel(BlockLevel.PRO)
                .build();

        assertEquals("https://example.com", request.getUrl());
        assertEquals("Desktop HD", request.getDevice());
        assertTrue(request.getFullPage());
        assertEquals(ImageFormat.PNG, request.getFormat());
        assertEquals(90, request.getQuality());
        assertEquals(1000, request.getDelay());
        assertEquals("#content", request.getWaitFor());
        assertEquals(WaitUntil.NETWORK_IDLE, request.getWaitUntil());
        assertEquals(30000, request.getTimeout());
        assertTrue(request.getDarkMode());
        assertEquals("body { background: white; }", request.getCustomCss());
        assertEquals(List.of(".ad", ".banner"), request.getHideSelectors());
        assertEquals("#main", request.getSelector());
        assertTrue(request.getBlockAds());
        assertTrue(request.getBlockCookieBanners());
        assertEquals(BlockLevel.PRO, request.getBlockLevel());
    }

    @Test
    void urlIsRequired() {
        assertThrows(IllegalArgumentException.class, () ->
                ScreenshotRequest.builder().build()
        );
    }

    @Test
    void urlCannotBeBlank() {
        assertThrows(IllegalArgumentException.class, () ->
                ScreenshotRequest.builder().url("  ").build()
        );
    }

    @Test
    void addHideSelectorBuildsListIncrementally() {
        ScreenshotRequest request = ScreenshotRequest.builder()
                .url("https://example.com")
                .addHideSelector(".ad")
                .addHideSelector(".banner")
                .addHideSelector(".popup")
                .build();

        assertEquals(List.of(".ad", ".banner", ".popup"), request.getHideSelectors());
    }

    @Test
    void serializesToJson() throws Exception {
        ScreenshotRequest request = ScreenshotRequest.builder()
                .url("https://example.com")
                .device("Desktop HD")
                .fullPage(true)
                .format(ImageFormat.PNG)
                .build();

        String json = objectMapper.writeValueAsString(request);

        assertTrue(json.contains("\"url\":\"https://example.com\""));
        assertTrue(json.contains("\"device\":\"Desktop HD\""));
        assertTrue(json.contains("\"fullPage\":true"));
        assertTrue(json.contains("\"format\":\"png\""));
    }

    @Test
    void nullFieldsNotSerialized() throws Exception {
        ScreenshotRequest request = ScreenshotRequest.builder()
                .url("https://example.com")
                .build();

        String json = objectMapper.writeValueAsString(request);

        assertFalse(json.contains("device"));
        assertFalse(json.contains("fullPage"));
        assertFalse(json.contains("format"));
    }
}
