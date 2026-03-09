package com.mini.project.controller;

import com.mini.project.dto.LoginReq;
import com.mini.project.dto.OtpReq;
import com.mini.project.dto.RegisterReq;
import com.mini.project.model.UserEntity;
import com.mini.project.service.AuthService;
import com.mini.project.utility.Message;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autentikasi", description = "Layanan Login dan Register Kerajaan Asura")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Operation(summary = "Meminta kode OTP", description = "Mengirimkan kode 6 angka ke email user (Cooldown 15 menit)")
    @PostMapping("/getOtp")
    public ResponseEntity<?> getOtp(@Valid @RequestBody OtpReq payload) {
        try {
            authService.generateAndSendOtp(payload.getEmail());
            // Gunakan utilitas Message yang sudah kita buat
            return new Message().success(
                    "Kode OTP berhasil dikirim ke " + payload.getEmail() + "!", null, 200);
        } catch (Exception e) {
            return new Message().error(e.getMessage(), 400);
        }
    }

    @Operation(summary = "Pendaftaran User Baru", description = "Menyimpan data user jika OTP valid")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReq payload) {
        try {
            UserEntity hasil = authService.registerUser(payload);
            return new Message().success("Registrasi berhasil!", hasil, 201);
        } catch (Exception e) {
            return new Message().error(e.getMessage(), 400);
        }
    }

    @Operation(summary = "Login User", description = "Masuk menggunakan Email dan Password")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq payload) {
        try {
            Map<String,Object> hasil = authService.loginUser(payload);
            return new Message().success("Login Sukses!", hasil, 200);
        } catch (Exception e) {
            return new Message().error(e.getMessage(), 401);
        }
    }
}