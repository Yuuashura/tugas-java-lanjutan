package com.mini.project.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ClientService {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8000").build();

    public Map<String, Object> getMapData(String uri, Object... params) throws Exception {
        try {
            return webClient.get()
                    .uri(uri, params)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            throw new Exception("Koneksi ke Kerajaan Barang gagal! Pastikan Port 8000 menyala. Error: " + e.getMessage());
        }
    }

}
