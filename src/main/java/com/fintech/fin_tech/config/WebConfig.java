package com.fintech.fin_tech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        long MAX_AGE_SECS = 3600; // 1 saat

        registry.addMapping("/api/**") // API endpoint'lerinizin başladığı yol (örn: /api/auth, /api/v1/transactions)
                .allowedOrigins("http://localhost:5173") // Frontend'inizin çalıştığı tam adres ve port
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // İzin verilen HTTP metotları
                .allowedHeaders("*") // Tüm header'lara izin ver
                .allowCredentials(true) // Kimlik bilgisi içeren isteklere (JWT token) izin ver
                .maxAge(MAX_AGE_SECS); // Preflight (OPTIONS) isteğinin ne kadar süreyle cache'leneceği
    }
}