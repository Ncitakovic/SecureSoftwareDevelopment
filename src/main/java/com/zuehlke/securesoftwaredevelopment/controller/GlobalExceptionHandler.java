package com.zuehlke.securesoftwaredevelopment.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.slf4j.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Log stack trace for internal review
        logger.error("Unhandled exception occurred", ex);
        // Do not expose stack trace to user (CWE-489)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal error occurred. Please contact support.");
    }
}
