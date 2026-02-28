package com.tugas.penjualan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugas.penjualan.payload.req.EmailPayloadReq;
import com.tugas.penjualan.payload.res.EmailPayloadRes;
import com.tugas.penjualan.service.EmailService;
import com.tugas.penjualan.utility.Message;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailPayloadReq payload) {
        try {
            // 1. Lempar JSON dari Frontend ke Service untuk dieksekusi
            EmailPayloadRes hasil = emailService.sendMail(payload);
            
            // 2. Bungkus hasilnya dengan utilitas Message Paduka yang elegan
            return new Message().success("Pesan gaib berhasil dikirim!", hasil, 200);
            
        } catch (Exception e) {
            // Jika gagal (misal salah password atau email tujuan tidak ada)
            return new Message().error(e.getMessage(), 500);
        }
    }
}