package com.medicare.neulpeum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000",
                        "http://0.0.0.0:3000",
                        "https://d14yvif52hqxqx.cloudfront.net")
                .allowedMethods("GET","POST","PUT","DELETE", "PATCH")
                .allowCredentials(true);

    }
}
