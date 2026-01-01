package com.allscreenshots.sdk.client;

import com.allscreenshots.sdk.exception.*;
import com.allscreenshots.sdk.model.ErrorResponse;
import com.allscreenshots.sdk.util.RetryConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Internal HTTP client for making API requests.
 */
class HttpClient {

    private static final String DEFAULT_BASE_URL = "https://api.allscreenshots.com";
    private static final String API_KEY_HEADER = "X-API-Key";
    private static final String USER_AGENT = "allscreenshots-sdk-java/1.0.0";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String apiKey;
    private final RetryConfig retryConfig;

    HttpClient(String baseUrl, String apiKey, Duration connectTimeout, Duration readTimeout,
               Duration writeTimeout, RetryConfig retryConfig, boolean enableLogging) {
        this.baseUrl = baseUrl != null ? baseUrl : DEFAULT_BASE_URL;
        this.apiKey = apiKey;
        this.retryConfig = retryConfig != null ? retryConfig : RetryConfig.defaultConfig();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout.toMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout.toMillis(), TimeUnit.MILLISECONDS);

        if (enableLogging) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(loggingInterceptor);
        }

        this.httpClient = clientBuilder.build();

        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    <T> T get(String path, Class<T> responseType) {
        Request request = buildRequest(path)
                .get()
                .build();
        return executeWithRetry(request, responseType);
    }

    <T> T get(String path, JavaType responseType) {
        Request request = buildRequest(path)
                .get()
                .build();
        return executeWithRetry(request, responseType);
    }

    <T> T post(String path, Object body, Class<T> responseType) {
        RequestBody requestBody = createJsonBody(body);
        Request request = buildRequest(path)
                .post(requestBody)
                .build();
        return executeWithRetry(request, responseType);
    }

    byte[] postForBinary(String path, Object body) {
        RequestBody requestBody = createJsonBody(body);
        Request request = buildRequest(path)
                .post(requestBody)
                .build();
        return executeForBinaryWithRetry(request);
    }

    <T> T put(String path, Object body, Class<T> responseType) {
        RequestBody requestBody = createJsonBody(body);
        Request request = buildRequest(path)
                .put(requestBody)
                .build();
        return executeWithRetry(request, responseType);
    }

    void delete(String path) {
        Request request = buildRequest(path)
                .delete()
                .build();
        executeWithRetry(request, Void.class);
    }

    byte[] getBinary(String path) {
        Request request = buildRequest(path)
                .get()
                .build();
        return executeForBinaryWithRetry(request);
    }

    private Request.Builder buildRequest(String path) {
        String url = baseUrl + path;
        Request.Builder builder = new Request.Builder()
                .url(url)
                .header("User-Agent", USER_AGENT);

        if (apiKey != null && !apiKey.isBlank()) {
            builder.header(API_KEY_HEADER, apiKey);
        }

        return builder;
    }

    private RequestBody createJsonBody(Object body) {
        if (body == null) {
            return RequestBody.create("", JSON);
        }
        try {
            String json = objectMapper.writeValueAsString(body);
            return RequestBody.create(json, JSON);
        } catch (JsonProcessingException e) {
            throw new AllscreenshotsException("Failed to serialize request body", e);
        }
    }

    private <T> T executeWithRetry(Request request, Class<T> responseType) {
        AllscreenshotsException lastException = null;

        for (int attempt = 0; attempt <= retryConfig.getMaxRetries(); attempt++) {
            try {
                return execute(request, responseType);
            } catch (RateLimitException | NetworkException e) {
                lastException = e;
                if (attempt < retryConfig.getMaxRetries()) {
                    sleep(retryConfig.getDelayForAttempt(attempt));
                }
            } catch (ApiException e) {
                // Only retry on 5xx errors
                if (e.getStatusCode() != null && e.getStatusCode() >= 500) {
                    lastException = e;
                    if (attempt < retryConfig.getMaxRetries()) {
                        sleep(retryConfig.getDelayForAttempt(attempt));
                    }
                } else {
                    throw e;
                }
            }
        }

        throw lastException != null ? lastException : new AllscreenshotsException("Request failed after retries");
    }

    private <T> T executeWithRetry(Request request, JavaType responseType) {
        AllscreenshotsException lastException = null;

        for (int attempt = 0; attempt <= retryConfig.getMaxRetries(); attempt++) {
            try {
                return execute(request, responseType);
            } catch (RateLimitException | NetworkException e) {
                lastException = e;
                if (attempt < retryConfig.getMaxRetries()) {
                    sleep(retryConfig.getDelayForAttempt(attempt));
                }
            } catch (ApiException e) {
                if (e.getStatusCode() != null && e.getStatusCode() >= 500) {
                    lastException = e;
                    if (attempt < retryConfig.getMaxRetries()) {
                        sleep(retryConfig.getDelayForAttempt(attempt));
                    }
                } else {
                    throw e;
                }
            }
        }

        throw lastException != null ? lastException : new AllscreenshotsException("Request failed after retries");
    }

    private byte[] executeForBinaryWithRetry(Request request) {
        AllscreenshotsException lastException = null;

        for (int attempt = 0; attempt <= retryConfig.getMaxRetries(); attempt++) {
            try {
                return executeForBinary(request);
            } catch (RateLimitException | NetworkException e) {
                lastException = e;
                if (attempt < retryConfig.getMaxRetries()) {
                    sleep(retryConfig.getDelayForAttempt(attempt));
                }
            } catch (ApiException e) {
                if (e.getStatusCode() != null && e.getStatusCode() >= 500) {
                    lastException = e;
                    if (attempt < retryConfig.getMaxRetries()) {
                        sleep(retryConfig.getDelayForAttempt(attempt));
                    }
                } else {
                    throw e;
                }
            }
        }

        throw lastException != null ? lastException : new AllscreenshotsException("Request failed after retries");
    }

    private <T> T execute(Request request, Class<T> responseType) {
        try (Response response = httpClient.newCall(request).execute()) {
            handleErrors(response);

            if (responseType == Void.class || response.body() == null) {
                return null;
            }

            String responseBody = response.body().string();
            if (responseBody.isEmpty()) {
                return null;
            }

            return objectMapper.readValue(responseBody, responseType);
        } catch (IOException e) {
            throw new NetworkException("Network error: " + e.getMessage(), e);
        }
    }

    private <T> T execute(Request request, JavaType responseType) {
        try (Response response = httpClient.newCall(request).execute()) {
            handleErrors(response);

            if (response.body() == null) {
                return null;
            }

            String responseBody = response.body().string();
            if (responseBody.isEmpty()) {
                return null;
            }

            return objectMapper.readValue(responseBody, responseType);
        } catch (IOException e) {
            throw new NetworkException("Network error: " + e.getMessage(), e);
        }
    }

    private byte[] executeForBinary(Request request) {
        try (Response response = httpClient.newCall(request).execute()) {
            handleErrors(response);

            if (response.body() == null) {
                throw new ApiException("Empty response body", response.code());
            }

            return response.body().bytes();
        } catch (IOException e) {
            throw new NetworkException("Network error: " + e.getMessage(), e);
        }
    }

    private void handleErrors(Response response) throws IOException {
        if (response.isSuccessful()) {
            return;
        }

        int statusCode = response.code();
        String responseBody = response.body() != null ? response.body().string() : "";

        ErrorResponse errorResponse = null;
        try {
            if (!responseBody.isEmpty()) {
                errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
            }
        } catch (JsonProcessingException ignored) {
            // Could not parse error response
        }

        String message = errorResponse != null && errorResponse.getMessage() != null
                ? errorResponse.getMessage()
                : "API request failed with status " + statusCode;
        String errorCode = errorResponse != null ? errorResponse.getCode() : null;

        switch (statusCode) {
            case 400:
                throw new ValidationException(message, errorCode != null ? errorCode : "VALIDATION_ERROR");
            case 401:
                throw new AuthenticationException(message, errorCode != null ? errorCode : "AUTHENTICATION_ERROR");
            case 403:
                throw new AuthenticationException(message, errorCode != null ? errorCode : "FORBIDDEN");
            case 429:
                String retryAfterHeader = response.header("Retry-After");
                Long retryAfter = retryAfterHeader != null ? Long.parseLong(retryAfterHeader) : null;
                throw new RateLimitException(message, retryAfter);
            default:
                throw new ApiException(message, errorCode != null ? errorCode : "API_ERROR", statusCode, responseBody);
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new AllscreenshotsException("Interrupted during retry delay", e);
        }
    }
}
