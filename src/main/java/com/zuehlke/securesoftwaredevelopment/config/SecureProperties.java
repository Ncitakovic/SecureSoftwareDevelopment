package com.zuehlke.securesoftwaredevelopment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "secure")
public class SecureProperties {
    /**
     * Admin password loaded securely from environment or config, not hardcoded.
     */
    private String adminPassword;

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}