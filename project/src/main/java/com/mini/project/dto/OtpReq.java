package com.mini.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OtpReq {
    
    // Sihir validasi: Tidak boleh kosong dan formatnya harus email beneran!
    @NotBlank(message = "Email tidak boleh kosong, Paduka!")
    @Email(message = "Format email tidak valid!")
    private String email;
}