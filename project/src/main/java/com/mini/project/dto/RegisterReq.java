package com.mini.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterReq {

    @NotBlank(message = "Email wajib diisi!")
    @Email(message = "Format email tidak valid!")
    private String email;

    @NotBlank(message = "Nama lengkap tidak boleh kosong!")
    private String namaLengkap;

    @NotBlank(message = "Jenis kelamin wajib dipilih!")
    private String jenisKelamin;

    @NotBlank(message = "Tanggal lahir wajib diisi!")
    private String tanggalLahir;

    @NotBlank(message = "Password wajib diisi!")
    private String password;

    @NotBlank(message = "Kode OTP tidak boleh kosong!")
    private String otp;
}