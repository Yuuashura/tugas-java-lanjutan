package com.tugas.email.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tugas.email.payload.req.EmailRequestDto;

@Service
public class EmailService {

    // Spring Boot secara otomatis menyuntikkan konfigurasi dari application.properties
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(EmailRequestDto request) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());
        
        // Eksekusi pengiriman email
        mailSender.send(message);
    }
}