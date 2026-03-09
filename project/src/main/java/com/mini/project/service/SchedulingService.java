package com.mini.project.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mini.project.dto.OtpInfo;
import com.mini.project.repo.UserRepository;
import jakarta.mail.internet.MimeMessage;

@Component
@Service
public class SchedulingService {

    @Autowired
    private JavaMailSender mailSender;

    // F I X E D   R A T E
    // @Scheduled(fixedDelay = 10000)
    public void generateAndSendOtp() throws Exception {

        String email = "pub.test.mail@mailinator.com";
        String htmlContent = "<h1>Hallo Saya Yudis</h1>" + 
        "<span>Ini kode Pengenalan kamu : </span>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email);
        helper.setSubject("Halloooooo");
        message.setSubject("Hallo everyone");
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }

    // @Scheduled(fixedDelay = 1000)
    public void cetak(){
        System.out.println("FIXED DELAY : "+ LocalDateTime.now().getMinute() + " : " + LocalDateTime.now().getSecond());
    }

    // @Scheduled(cron = "10 * * * * ?")
    public void cron(){
        System.out.println("CRON : " + LocalDateTime.now().getMinute() + " : " + LocalDateTime.now().getSecond());
    }

}
