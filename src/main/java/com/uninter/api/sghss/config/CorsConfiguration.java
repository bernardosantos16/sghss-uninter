package com.uninter.api.sghss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://127.0.0.1:5500", 
                    "http://localhost:5500",
                    "http://127.0.0.1:3000", 
                    "http://localhost:3000",
                    "http://127.0.0.1:8080", 
                    "http://localhost:8080",
                    "file://"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);

    }
}
