package com.mini.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // Menandakan ini adalah pusat pengaturan
public class WebClientConfig {

    @Bean // Mendaftarkan WebClient agar bisa di-@Autowired
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}