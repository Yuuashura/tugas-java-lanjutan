package com.tugas.penjualan.service;

import java.time.LocalDateTime;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tugas.penjualan.payload.req.EmailPayloadReq;
import com.tugas.penjualan.payload.res.EmailPayloadRes;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Value("${smtp.host}")
    private String smtpHost;

    @Value("${smtp.port}")
    private String smtpPort;

    public EmailPayloadRes sendMail(EmailPayloadReq payloadReq) throws Exception {
        try {
            // 1. Siapkan konfigurasi properti SMTP
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);

            // 2. Lakukan Autentikasi menggunakan data dari Payload (JSON)
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // Gunakan App Password jika memakai Gmail
                    return new PasswordAuthentication(payloadReq.getSenderEmail(), payloadReq.getSenderPassword());
                }
            });

            // 3. Meracik wujud fisik suratnya
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(payloadReq.getSenderEmail()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(payloadReq.getRecipientEmail()));
            message.setSubject(payloadReq.getSubject());
            // ✅ INI YANG BENAR (Ganti jadi setContent)
            message.setContent(payloadReq.getBody(), "text/html; charset=utf-8");
            // 4. Kirim Suratnya!
            Transport.send(message);

            // 5. Siapkan balikan (Response) untuk dilaporkan ke Controller
            EmailPayloadRes responseData = new EmailPayloadRes();
            responseData.setRecipientEmail(payloadReq.getRecipientEmail());
            responseData.setStatusPengiriman("Berhasil Diterbangkan! 🕊️");
            responseData.setWaktuKirim(LocalDateTime.now().toString()); // Mencatat waktu sukses

            return responseData;

        } catch (Exception e) {
            throw new Exception("Sihir pengiriman surat gagal: " + e.getMessage());
        }
    }
}