package com.zuehlke.securesoftwaredevelopment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/auth")
public class LoginController2 {
    @Autowired
    Environment env;

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        javax.servlet.http.HttpServletResponse response) {

        if ("admin".equals(username) && "admin".equals(password)) {

            javax.servlet.http.Cookie cookie = new Cookie("sessionUser", username);

            // ✅ CWE-1004 fix
            cookie.setHttpOnly(true);

            // ✅ CWE-614 fix
            cookie.setSecure(true);

            // ✅ dodatno (best practice)
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60); // 1h

            response.addCookie(cookie);

            return "Login successful (SECURE)";
        }

        return "Invalid credentials";
    }

}