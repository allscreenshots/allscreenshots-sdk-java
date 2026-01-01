package com.allscreenshots.sdk.unit;

import com.allscreenshots.sdk.util.RetryConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetryConfigTest {

    @Test
    void defaultConfig() {
        RetryConfig config = RetryConfig.defaultConfig();

        assertEquals(3, config.getMaxRetries());
        assertEquals(1000, config.getInitialDelayMs());
        assertEquals(30000, config.getMaxDelayMs());
        assertEquals(2.0, config.getMultiplier());
    }

    @Test
    void noRetriesConfig() {
        RetryConfig config = RetryConfig.noRetries();

        assertEquals(0, config.getMaxRetries());
    }

    @Test
    void customConfig() {
        RetryConfig config = RetryConfig.builder()
                .maxRetries(5)
                .initialDelayMs(500)
                .maxDelayMs(60000)
                .multiplier(3.0)
                .build();

        assertEquals(5, config.getMaxRetries());
        assertEquals(500, config.getInitialDelayMs());
        assertEquals(60000, config.getMaxDelayMs());
        assertEquals(3.0, config.getMultiplier());
    }

    @Test
    void exponentialBackoffCalculation() {
        RetryConfig config = RetryConfig.builder()
                .initialDelayMs(1000)
                .multiplier(2.0)
                .maxDelayMs(30000)
                .build();

        assertEquals(1000, config.getDelayForAttempt(0));
        assertEquals(2000, config.getDelayForAttempt(1));
        assertEquals(4000, config.getDelayForAttempt(2));
        assertEquals(8000, config.getDelayForAttempt(3));
        assertEquals(16000, config.getDelayForAttempt(4));
    }

    @Test
    void delayIsCappedAtMaxDelay() {
        RetryConfig config = RetryConfig.builder()
                .initialDelayMs(1000)
                .multiplier(10.0)
                .maxDelayMs(5000)
                .build();

        assertEquals(1000, config.getDelayForAttempt(0));
        assertEquals(5000, config.getDelayForAttempt(1)); // Would be 10000 but capped
        assertEquals(5000, config.getDelayForAttempt(2)); // Would be 100000 but capped
    }
}
