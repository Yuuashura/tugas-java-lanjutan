package com.mini.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor // Sihir Lombok untuk membuat Constructor otomatis
public class OtpInfo {
    private String otpCode;
    private LocalDateTime requestTime;
}