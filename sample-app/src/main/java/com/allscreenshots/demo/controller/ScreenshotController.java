package com.allscreenshots.demo.controller;

import com.allscreenshots.sdk.client.AllscreenshotsClient;
import com.allscreenshots.sdk.exception.AllscreenshotsException;
import com.allscreenshots.sdk.model.ScreenshotRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@Controller
public class ScreenshotController {

    private final AllscreenshotsClient client;

    public ScreenshotController(AllscreenshotsClient client) {
        this.client = client;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/screenshot")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> captureScreenshot(@RequestBody ScreenshotRequestDto request) {
        try {
            byte[] image = client.screenshots().capture(
                    ScreenshotRequest.builder()
                            .url(request.url())
                            .device(request.device())
                            .fullPage(request.fullPage() != null && request.fullPage())
                            .build()
            );

            String base64Image = Base64.getEncoder().encodeToString(image);
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "image", "data:image/png;base64," + base64Image
            ));
        } catch (AllscreenshotsException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "error", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "success", false,
                    "error", "An unexpected error occurred: " + e.getMessage()
            ));
        }
    }

    public record ScreenshotRequestDto(String url, String device, Boolean fullPage) {}
}
