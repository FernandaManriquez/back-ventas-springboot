package com.citt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthController {

    @GetMapping("/api/v1/ventas/health")
    public Map<String, String> health() {
        return Map.of("service", "ventas", "status", "OK");
    }
}
