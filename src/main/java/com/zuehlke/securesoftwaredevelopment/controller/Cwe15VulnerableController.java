package com.zuehlke.securesoftwaredevelopment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/cwe15/vulnerable")
public class Cwe15VulnerableController {

    private boolean debugEnabled = false;
    private boolean maintenanceMode = true;

    /*
     * RANJIVO:
     * Svaki korisnik može da menja bezbednosno relevantna podešavanja.
     */
    @PostMapping("/configuration")
    public Map<String, Object> changeConfiguration(
            @RequestParam(required = false) Boolean debugEnabled,
            @RequestParam(required = false) Boolean maintenanceMode) {

        if (debugEnabled != null) {
            this.debugEnabled = debugEnabled;
        }

        if (maintenanceMode != null) {
            this.maintenanceMode = maintenanceMode;
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("debugEnabled", this.debugEnabled);
        response.put("maintenanceMode", this.maintenanceMode);

        return response;
    }

    /*
     * Endpoint koji otkriva interne informacije kada je debug uključen.
     */
    @GetMapping("/debug")
    public ResponseEntity<Map<String, Object>> debugInformation() {

        if (!debugEnabled) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("error", "Debug mode is disabled.");

            return ResponseEntity.status(403).body(response);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("databaseUrl", "jdbc:h2:mem:testdb");
        response.put("databaseUser", "sa");
        response.put("activeProfile", "dev");
        response.put("h2ConsoleEnabled", true);

        return ResponseEntity.ok(response);
    }

    /*
     * Simulacija operacije koja nije dozvoljena tokom održavanja.
     */
    @PostMapping("/cars/delete")
    public ResponseEntity<Map<String, Object>> deleteCar(
            @RequestParam Long carId) {

        if (maintenanceMode) {
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("error", "Application is in maintenance mode.");

            return ResponseEntity.status(503).body(response);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Car successfully deleted.");
        response.put("carId", carId);

        return ResponseEntity.ok(response);
    }
}