package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.model.ViewportConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewportConfigTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void buildViewportConfig() {
        ViewportConfig config = ViewportConfig.builder()
                .width(1920)
                .height(1080)
                .deviceScaleFactor(2)
                .build();

        assertEquals(1920, config.getWidth());
        assertEquals(1080, config.getHeight());
        assertEquals(2, config.getDeviceScaleFactor());
    }

    @Test
    void serializesToJson() throws Exception {
        ViewportConfig config = ViewportConfig.builder()
                .width(1920)
                .height(1080)
                .deviceScaleFactor(2)
                .build();

        String json = objectMapper.writeValueAsString(config);

        assertTrue(json.contains("\"width\":1920"));
        assertTrue(json.contains("\"height\":1080"));
        assertTrue(json.contains("\"deviceScaleFactor\":2"));
    }
}
