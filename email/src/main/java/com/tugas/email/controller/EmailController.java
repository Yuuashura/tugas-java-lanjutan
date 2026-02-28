package com.tugas.email.controller;

import com.tugas.email.payload.req.EmailRequestDto;
import com.tugas.email.payload.res.ApiResponse;
import com.tugas.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendEmail(@RequestBody EmailRequestDto request) {
        try {
            // Memanggil service untuk mengirim email
            emailService.sendSimpleEmail(request);
            
            // Mengembalikan respons sukses ke Postman
            ApiResponse response = new ApiResponse(true, "Email berhasil dikirim ke " + request.getTo());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            // Mengembalikan respons gagal ke Postman
            ApiResponse response = new ApiResponse(false, "Gagal mengirim email: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}