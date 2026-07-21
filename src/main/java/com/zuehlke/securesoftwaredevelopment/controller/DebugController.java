package com.zuehlke.securesoftwaredevelopment.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@RestController
@RequestMapping("/auth/debug")
public class DebugController {

    @GetMapping("/users")
    public String debugUsers() {
        return "admin, user1, user2";
    }

    @GetMapping("/env")
    public String debugEnv() {
        return System.getenv().toString();
    }
}
