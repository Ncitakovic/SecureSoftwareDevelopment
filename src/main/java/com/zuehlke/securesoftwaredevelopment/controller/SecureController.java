package com.zuehlke.securesoftwaredevelopment.controller;

import com.zuehlke.securesoftwaredevelopment.config.SecureProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import javax.servlet.http.*;

@RestController
@RequestMapping("/secure-demo")
public class SecureController {

    @Autowired
    private SecureProperties secureProperties;

    // Secure login: cookie flags enforced globally (CWE-1004, CWE-614)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, HttpServletResponse response) {
        // Cookie flags are set globally via SecurityStrengthConfigg
        return ResponseEntity.ok("Logged in, session cookie set securely");
    }

    // Do NOT expose environment variables (CWE-526)
    @GetMapping("/env")
    public ResponseEntity<String> getEnv() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access to environment variables is not allowed.");
    }

    // Use injected secret, not hardcoded (CWE-260)
    @GetMapping("/backdoor")
    public ResponseEntity<String> backdoor(@RequestParam String password) {
        if (password.equals(secureProperties.getAdminPassword())) {
            return ResponseEntity.ok("Backdoor access granted!");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    // No debug stack trace exposed (CWE-489)
    @GetMapping("/error-demo")
    public ResponseEntity<String> errorDemo() {
        throw new RuntimeException("Simulated exception");
    }
}
