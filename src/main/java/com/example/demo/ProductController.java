package com.example.demo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @GetMapping("/api/products")
    public Map<String, Object> getProduct(
            @RequestParam(defaultValue = "keyboard") String name,
            @RequestHeader(value = "X-Client-Trace", required = false, defaultValue = "no-trace") String traceHeader,
            HttpServletRequest request) {

        String userAgent = request.getHeader("User-Agent");
        logger.info("Product lookup requested. traceHeader={}, productName={}, userAgent={}",
                traceHeader, name, userAgent);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("product", name);
        response.put("status", "ok");
        response.put("traceHeader", traceHeader);
        response.put("message", "This endpoint is intentionally simple for classroom use.");
        return response;
    }
}
