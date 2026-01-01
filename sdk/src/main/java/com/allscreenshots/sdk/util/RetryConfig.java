package com.allscreenshots.sdk.util;

/**
 * Configuration for retry behavior with exponential backoff.
 */
public class RetryConfig {

    private final int maxRetries;
    private final long initialDelayMs;
    private final long maxDelayMs;
    private final double multiplier;

    private RetryConfig(Builder builder) {
        this.maxRetries = builder.maxRetries;
        this.initialDelayMs = builder.initialDelayMs;
        this.maxDelayMs = builder.maxDelayMs;
        this.multiplier = builder.multiplier;
    }

    /**
     * Returns the default retry configuration.
     *
     * @return default configuration with 3 retries
     */
    public static RetryConfig defaultConfig() {
        return builder().build();
    }

    /**
     * Returns a configuration with retries disabled.
     *
     * @return configuration with no retries
     */
    public static RetryConfig noRetries() {
        return builder().maxRetries(0).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public long getInitialDelayMs() {
        return initialDelayMs;
    }

    public long getMaxDelayMs() {
        return maxDelayMs;
    }

    public double getMultiplier() {
        return multiplier;
    }

    /**
     * Calculates the delay for a given retry attempt.
     *
     * @param attempt the retry attempt number (0-based)
     * @return the delay in milliseconds
     */
    public long getDelayForAttempt(int attempt) {
        long delay = (long) (initialDelayMs * Math.pow(multiplier, attempt));
        return Math.min(delay, maxDelayMs);
    }

    public static class Builder {
        private int maxRetries = 3;
        private long initialDelayMs = 1000;
        private long maxDelayMs = 30000;
        private double multiplier = 2.0;

        /**
         * Sets the maximum number of retries.
         *
         * @param maxRetries maximum retries (0 to disable)
         * @return this builder
         */
        public Builder maxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        /**
         * Sets the initial delay before the first retry.
         *
         * @param initialDelayMs delay in milliseconds
         * @return this builder
         */
        public Builder initialDelayMs(long initialDelayMs) {
            this.initialDelayMs = initialDelayMs;
            return this;
        }

        /**
         * Sets the maximum delay between retries.
         *
         * @param maxDelayMs maximum delay in milliseconds
         * @return this builder
         */
        public Builder maxDelayMs(long maxDelayMs) {
            this.maxDelayMs = maxDelayMs;
            return this;
        }

        /**
         * Sets the multiplier for exponential backoff.
         *
         * @param multiplier the multiplier (typically 2.0)
         * @return this builder
         */
        public Builder multiplier(double multiplier) {
            this.multiplier = multiplier;
            return this;
        }

        public RetryConfig build() {
            return new RetryConfig(this);
        }
    }
}
