package com.zuehlke.securesoftwaredevelopment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/cwe15/secure")
public class Cwe15SecureController {

    @Value("${demo.debug-enabled:false}")
    private boolean debugEnabled;

    @Value("${demo.maintenance-mode:true}")
    private boolean maintenanceMode;

    /*
     * Ne postoji endpoint kojim korisnik menja konfiguraciju.
     */
    @GetMapping("/debug")
    public ResponseEntity<Map<String, Object>> debugInformation() {

        if (!debugEnabled) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("error", "Debug functionality is disabled.");

            return ResponseEntity.status(403).body(response);
        }

        Map<String, Object> response = new LinkedHashMap<>();

        /*
         * Čak i kada je debug dozvoljen, ne prikazujemo lozinke
         * i druge poverljive konfiguracione vrednosti.
         */
        response.put("application", "Secure Software Development");
        response.put("status", "Development diagnostics enabled");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cars/delete")
    public ResponseEntity<Map<String, Object>> deleteCar(
            @RequestParam Long carId) {

        if (maintenanceMode) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("error", "Operation unavailable during maintenance.");

            return ResponseEntity.status(503).body(response);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Car successfully deleted.");
        response.put("carId", carId);

        return ResponseEntity.ok(response);
    }
}
