package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class PreviewController {

    private static final Logger logger = LogManager.getLogger(PreviewController.class);
    private final RestTemplate restTemplate;

    public PreviewController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/api/preview")
    public ResponseEntity<Map<String, Object>> preview(@RequestBody PreviewRequest request) {
        logger.info("Fetching preview for URL={}", request.getUrl());

        String body = restTemplate.getForObject(request.getUrl(), String.class);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("requestedUrl", request.getUrl());
        response.put("preview", body);
        return ResponseEntity.ok(response);
    }
}
