package com.tugas.penjualan.controller;

import com.tugas.penjualan.payload.res.BarangPayloadRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.tugas.penjualan.payload.res.PenjualanPayloadRes;

@Controller
@RequestMapping("/penjualan")
public class PenjualanController {
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8000").build();

    @GetMapping("/getStokByPenjualan")
    public ResponseEntity getStock(@RequestParam String idPenjualan) {
        System.out.println("Id Penjualan : " + idPenjualan);
        try {
            PenjualanPayloadRes res = webClient.get()
                    .uri("/barang/cekStockByPenjualan?idPenjualan={idPenjualan}", idPenjualan)
                    .retrieve()
                    .bodyToMono(PenjualanPayloadRes.class)
                    .block();

            System.out.println("Res : " + res);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Pesan error: " + e.getMessage());
            System.out.println("Penyebab error: " + e.getCause());
            if (e.getCause() != null && e.getCause().toString().contains("Connection refused")) {
                return new ResponseEntity("koneksi gagal", HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity("Gagal karena lain hal", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("getStockByBarang")
    public ResponseEntity getStockByBarang(@RequestParam String idBarang) {
        System.out.println("Id Barang : " + idBarang);
        try {
            BarangPayloadRes res = webClient.get()
                    .uri("/barang/cekStock?idBarang={idBarang}", idBarang)
                    .retrieve()
                    .bodyToMono(BarangPayloadRes.class)
                    .block();

            System.out.println("Res : " + res);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Pesan error: " + e.getMessage());
            System.out.println("Penyebab error: " + e.getCause());
            if (e.getCause() != null && e.getCause().toString().contains("Connection refused")) {
                return new ResponseEntity("koneksi gagal", HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity("Gagal karena lain hal", HttpStatus.NOT_FOUND);
        }
    }
}
